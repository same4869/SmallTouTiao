package com.xun.smalltoutiao.appnews.utils;

import android.support.annotation.NonNull;

import com.xun.smalltoutiao.appnews.bean.NewsJokeContentBean;
import com.xun.smalltoutiao.appnews.binder.NewsJokeContentViewBinder;
import com.xun.smalltoutiao.libcomm.bean.CommLoadingBean;
import com.xun.smalltoutiao.libcomm.bean.CommLoadingEndBean;
import com.xun.smalltoutiao.libcomm.binder.CommLoadingEndViewBinder;
import com.xun.smalltoutiao.libcomm.binder.CommLoadingViewBinder;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by xunwang on 2017/11/30.
 */

public class NewsRegister {
    public static void registerJokeContentItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(NewsJokeContentBean.DataBean.GroupBean.class, new NewsJokeContentViewBinder());
        adapter.register(CommLoadingBean.class, new CommLoadingViewBinder());
        adapter.register(CommLoadingEndBean.class, new CommLoadingEndViewBinder());
    }
}
