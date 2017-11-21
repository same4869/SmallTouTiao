package com.xun.smalltoutiao.appmain.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xunwang on 2017/11/20.
 */

public class MainManifestEntity implements Serializable {

    private String version;

    private List<MainBundleEntity> bundles;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<MainBundleEntity> getBundles() {
        return bundles;
    }

    public void setBundles(List<MainBundleEntity> bundles) {
        this.bundles = bundles;
    }

}
