package com.xun.smalltoutiao.libcomm.utils;

import android.support.annotation.NonNull;

import com.xun.smalltoutiao.libcomm.BuildConfig;

import io.reactivex.functions.Consumer;

/**
 * Created by xunwang on 2017/11/30.
 */

public class CommErrorAction {
    @NonNull
    public static Consumer<Throwable> error() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                if (BuildConfig.DEBUG) {
                    throwable.printStackTrace();
                }
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
