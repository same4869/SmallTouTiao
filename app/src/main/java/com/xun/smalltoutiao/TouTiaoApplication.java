package com.xun.smalltoutiao;

import android.app.Application;

import net.wequick.small.Small;

/**
 * Created by xunwang on 2017/11/17.
 */

public class TouTiaoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Small.preSetUp(this);
    }
}
