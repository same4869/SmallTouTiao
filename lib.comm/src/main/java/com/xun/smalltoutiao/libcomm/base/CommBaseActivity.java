package com.xun.smalltoutiao.libcomm.base;

import android.content.Context;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by xunwang on 2017/11/17.
 */

public class CommBaseActivity extends RxAppCompatActivity{
    protected static Context context;

    public static Context getAppContext() {
        return context;
    }
}
