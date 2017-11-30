package com.xun.smalltoutiao.libcomm.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by xunwang on 2017/11/30.
 */

public interface CommIBaseView<T> {
    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
