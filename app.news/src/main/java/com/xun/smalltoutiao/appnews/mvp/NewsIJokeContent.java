package com.xun.smalltoutiao.appnews.mvp;

import com.xun.smalltoutiao.libcomm.mvp.CommIBaseListView;
import com.xun.smalltoutiao.libcomm.mvp.CommIBasePresenter;

/**
 * Created by xunwang on 2017/11/30.
 */

public interface NewsIJokeContent {

    interface View extends CommIBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends CommIBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData();

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter();

        void doShowNoMore();
    }
}
