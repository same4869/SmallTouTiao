package com.xun.smalltoutiao.appmain.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.xun.smalltoutiao.appmain.bean.MainPluginEntity;
import com.xun.smalltoutiao.appmain.bean.MainSmallEntity;
import com.xun.smalltoutiao.appmain.sp.MainSpManager;
import com.xun.smalltoutiao.libcomm.utils.CommFastJsonUtil;

import net.wequick.small.Small;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xunwang on 2017/11/20.
 */

public class MainSmallService extends IntentService {
    // 更新插件Url
    private static final String URL_UPDATES = "http://test-1251839428.cossh.myqcloud.com/plugin_main_1.0.1_update.json";
    // 增加插件Url
    private static final String URL_ADDITIONS = "http://test-1251839428.cossh.myqcloud.com/plugin_main_1.1.1_update.json";


    // 启动Small服务类型
    public static final String SMALL_CHECK_UPDATE = "small_check_update";
    public static final String SMALL_CHECK_ADD = "small_check_add";
    public static final String SMALL_DOWNLOAD_PLUGINS = "small_download_plugins";
    public static final String SMALL_UPDATE_BUNDLES = "small_update_bundles";


    // 启动Small状态类型
    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_START = 1;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_DOWNLOAD_SUCCESS = 3;
    public static final int STATUS_TOAST = 4;
    public static final int STATUS_FAILED = -1;


//    private LocalBroadcastManager mLocalBroadcastManager;
    private static MainSmallEntity mSmallEntity;
    private static List<MainPluginEntity> mPluginEntities;

    public MainSmallService() {
        super("MainSmallService");
//        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String small = intent.getStringExtra("small");
                switch (small) {
                    case SMALL_CHECK_UPDATE://检查更新
                        smallCheckUpdate(URL_UPDATES);
                        break;
                    case SMALL_CHECK_ADD://检查更新
                        smallCheckUpdate(URL_ADDITIONS);
                        break;
                    case SMALL_DOWNLOAD_PLUGINS://下载插件
                        smallDownloadPlugins();
                        break;
                    case SMALL_UPDATE_BUNDLES://更新插件
                        smallUpdateBundles();
                        break;
                    default:
                        break;
                }
            }
        }).start();
    }

    // Small 检查插件更新中...
    private boolean smallCheckUpdate(String urlString) {
        sendServiceStatus(STATUS_START, "正在请求配置文件...");
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, length));
            }
            String plugin_bundles = sb.toString();
            if (TextUtils.isEmpty(plugin_bundles)) {
                sendServiceStatus(STATUS_TOAST, "配置文件为空");
                return false;
            }

            mSmallEntity = CommFastJsonUtil.parseJson(plugin_bundles, MainSmallEntity.class);
            if (mSmallEntity == null) {
                sendServiceStatus(STATUS_TOAST, "解析配置文件失败");
                return false;
            }

            sendServiceStatus(STATUS_DEFAULT, "解析配置文件成功！");
            MainSpManager.savePluginBundles(getApplicationContext(), plugin_bundles);

            if (needUpdateOrAdd()) {
                sendServiceStatus(STATUS_DOWNLOADING, "启动下载插件服务...");
                Intent intent = new Intent(this, MainSmallService.class);
                intent.putExtra("small", SMALL_DOWNLOAD_PLUGINS);
                startService(intent);
            } else {
                sendServiceStatus(STATUS_DOWNLOAD_SUCCESS, "插件已是最新版本");
            }
        } catch (Exception e) {
            sendServiceStatus(STATUS_FAILED, "检查更新异常");
            e.printStackTrace();
        }
        return true;
    }

    // 下载 Small 插件
    private void smallDownloadPlugins() {
        try {
            int count = mPluginEntities.size();
            String filePath = getFilePathBySDCard() + File.separator;

            File destDir = new File(filePath);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            for (int i = 0; i < count; i++) {
                MainPluginEntity pluginEntity = mPluginEntities.get(i);
                String fileName = getFileNameByUrl(pluginEntity.getUrl());
                if (TextUtils.isEmpty(fileName)) {
                    continue;
                }
                sendServiceStatus(STATUS_DEFAULT, "下载插件：" + fileName);

                URL url = new URL(pluginEntity.getUrl());
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                InputStream is = urlConn.getInputStream();
                OutputStream os = new FileOutputStream(filePath + fileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            }
            sendServiceStatus(STATUS_DEFAULT, "下载插件完成！");
            sendServiceStatus(STATUS_DOWNLOAD_SUCCESS, "按返回键，重新启动APP，查看效果");
        } catch (Exception e) {
            sendServiceStatus(STATUS_FAILED, "下载插件异常");
            e.printStackTrace();
        }
    }

    // 更新 small 插件
    private boolean smallUpdateBundles() {
        try {
            String plugin_bundles = MainSpManager.getPluginBundles(getApplicationContext());
            if (TextUtils.isEmpty(plugin_bundles)) {
                return false;
            }
            mSmallEntity = CommFastJsonUtil.parseJson(plugin_bundles, MainSmallEntity.class);
            if (mSmallEntity == null) {
                return false;
            }
            boolean updateManifest = mSmallEntity.getManifest_code() > MainSpManager.getManifestCode(getApplicationContext());
            if (updateManifest) {
                // 更新注册表信息
                JSONObject smallObject = new JSONObject(plugin_bundles);
                JSONObject manifestObject = smallObject.has("manifest") ? smallObject.getJSONObject("manifest") : null;
                if (manifestObject != null) {
                    Small.updateManifest(manifestObject, false);
                    sendServiceStatus(STATUS_TOAST, "更新注册表成功！");
                }
            }
            if (needUpdateOrAdd()) {
                // 更新插件
                int count = mPluginEntities.size();
                for (int i = 0; i < count; i++) {
                    MainPluginEntity pluginEntity = mPluginEntities.get(i);
                    String fileName = getFileNameByUrl(pluginEntity.getUrl());
                    updateBundleThenDelete(pluginEntity.getPkg(), fileName);
                }
                if (updateManifest) {
                    MainSpManager.saveSmallAdd(getApplicationContext(), 1);
                    sendServiceStatus(STATUS_TOAST, "增加插件成功！");
                } else {
                    MainSpManager.saveSmallUpdate(getApplicationContext(), 1);
                    sendServiceStatus(STATUS_TOAST, "更新插件成功！");
                }
            }
            MainSpManager.saveManifestCode(getApplicationContext(), mSmallEntity.getManifest_code());
            MainSpManager.saveUpdatesCode(getApplicationContext(), mSmallEntity.getUpdates_code());
            MainSpManager.saveAdditionCode(getApplicationContext(), mSmallEntity.getAdditions_code());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // 更新插件后删除插件
    private void updateBundleThenDelete(final String pkgName, final String fileName) {
        try {
            String filePath = getFilePathBySDCard();
            File inFile = new File(filePath, fileName);
            if (!inFile.exists()) {
                return;
            }

            net.wequick.small.Bundle bundle = Small.getBundle(pkgName);
            File outFile = bundle.getPatchFile();

            InputStream is = new FileInputStream(inFile);
            OutputStream os = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

            os.flush();
            os.close();
            is.close();
            bundle.upgrade();
            inFile.delete();
            sendServiceStatus(STATUS_TOAST, "updateBundleThenDelete pkgName --> " + pkgName + " fileName --> " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化要更新的插件列表
    private boolean needUpdateOrAdd() {
        if (mSmallEntity == null) {
            return false;
        }
        boolean hasUpdates = mSmallEntity.getUpdates_code() > MainSpManager.getUpdatesCode(getApplicationContext());
        boolean hasAdditions = mSmallEntity.getAdditions_code() > MainSpManager.getAdditionCode(getApplicationContext());
        if (!hasUpdates && !hasAdditions) {
            return false;
        }
        mPluginEntities = new ArrayList<>();
        if (hasUpdates) {
            mPluginEntities.addAll(mSmallEntity.getUpdates());
        }
        if (hasAdditions) {
            mPluginEntities.addAll(mSmallEntity.getAdditions());
        }
        return true;
    }

    // 获得手机上存储插件路径
    private String getFilePathBySDCard() {
        String filePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filePath = Environment.getExternalStorageDirectory() + File.separator + "DroidSmall";
        } else {
            filePath = Environment.getDownloadCacheDirectory() + File.separator + "DroidSmall";
        }
        return filePath;
    }

    // 通过 URL 获得文件名
    private String getFileNameByUrl(String url) {
        if (TextUtils.isEmpty(url) || !url.endsWith(".so")) {
            return null;
        }
        String[] split = url.split("/");
        if (split.length == 0) {
            return null;
        }
        return split[split.length - 1];
    }

    // 发送状态信息
    private void sendServiceStatus(int status, String tip) {
        Log.d("------>", "【MainSmallService】tip: " + tip);
        Intent intent = new Intent("DroidSmall");
        intent.putExtra("status", status);
        intent.putExtra("tip", tip);
//        mLocalBroadcastManager.sendBroadcast(intent);
    }
}
