package com.xun.smalltoutiao.libcomm.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * Created by xunwang on 2017/11/30.
 */

public interface CommIBaseListView<T> extends CommIBaseView<T> {
    /**
     * 显示加载动画
     */
    @Override
    void onShowLoading();

    /**
     * 隐藏加载
     */
    @Override
    void onHideLoading();

    /**
     * 显示网络错误
     */
    @Override
    void onShowNetError();

    /**
     * 设置 presenter
     */
    @Override
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     */
    @Override
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
