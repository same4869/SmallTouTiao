package com.xun.smalltoutiao.appnews.mvp;

import com.xun.smalltoutiao.appnews.bean.NewsJokeCommentBean;
import com.xun.smalltoutiao.libcomm.mvp.CommIBaseListView;
import com.xun.smalltoutiao.libcomm.mvp.CommIBasePresenter;

import java.util.List;

/**
 * Created by xunwang on 2017/12/4.
 */

public interface NewsIJokeComment {
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
        void doLoadData(String... jokeId_Count);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<NewsJokeCommentBean.DataBean.RecentCommentsBean> commentsBeanList);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
