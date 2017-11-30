package com.xun.smalltoutiao.libcomm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xun.smalltoutiao.libcomm.mvp.CommIBasePresenter;
import com.xun.smalltoutiao.libcomm.mvp.CommIBaseView;

/**
 * Created by xunwang on 2017/11/30.
 */

public abstract class CommBaseFragment<T extends CommIBasePresenter> extends RxFragment implements CommIBaseView<T> {
    protected T presenter;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutId();

    /**
     * 初始化视图控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData() throws NullPointerException;

//    /**
//     * 初始化 Toolbar
//     */
//    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
//        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    /**
     * 绑定生命周期
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
