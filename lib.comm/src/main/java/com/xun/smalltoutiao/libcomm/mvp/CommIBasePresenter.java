package com.xun.smalltoutiao.libcomm.mvp;

/**
 * Created by xunwang on 2017/11/30.
 */

public interface CommIBasePresenter {
    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
