package com.xun.smalltoutiao.libcomm.base;

import android.os.Bundle;
import android.util.Log;

import com.xun.smalltoutiao.libcomm.mvp.CommIBasePresenter;

/**
 * Created by xunwang on 2017/11/30.
 */

public abstract class CommLazyLoadFragment<T extends CommIBasePresenter> extends CommBaseFragment<T> {
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("kkkkkkkk","isVisibleToUser --> " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        Log.d("kkkkkkkk", "isVisibleToUser --> " + isVisibleToUser + " isViewInitiated --> " + isViewInitiated + " isDataInitiated --> " + isDataInitiated + " forceUpdate -->" + forceUpdate);
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }
}
