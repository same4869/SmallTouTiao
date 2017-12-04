package com.xun.smalltoutiao.appnews.mvp;

import com.xun.smalltoutiao.appnews.api.NewsIJokeApi;
import com.xun.smalltoutiao.appnews.bean.NewsJokeCommentBean;
import com.xun.smalltoutiao.libcomm.utils.CommErrorAction;
import com.xun.smalltoutiao.libcomm.utils.CommRetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xunwang on 2017/12/4.
 */

public class NewsJokeCommentPresenter implements NewsIJokeComment.Presenter {
    private NewsIJokeComment.View view;
    private String jokeId;
    private int count = -1;
    private int offset = 0;
    private List<NewsJokeCommentBean.DataBean.RecentCommentsBean> commentsList = new ArrayList<>();

    public NewsJokeCommentPresenter(NewsIJokeComment.View view) {
        this.view = view;
    }

    @Override
    public void doLoadData(String... jokeId_Count) {

        try {
            if (null == this.jokeId) {
                this.jokeId = jokeId_Count[0];
            }
            if (-1 == this.count) {
                this.count = Integer.parseInt(jokeId_Count[1]);
            }
        } catch (Exception e) {
            CommErrorAction.print(e);
        }

        CommRetrofitFactory.getRetrofit().create(NewsIJokeApi.class).getJokeComment(jokeId, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<NewsJokeCommentBean, List<NewsJokeCommentBean.DataBean.RecentCommentsBean>>() {
                    @Override
                    public List<NewsJokeCommentBean.DataBean.RecentCommentsBean> apply(@NonNull NewsJokeCommentBean jokeCommentBean) throws Exception {
                        return jokeCommentBean.getData().getRecent_comments();
                    }
                })
                .compose(view.<List<NewsJokeCommentBean.DataBean.RecentCommentsBean>>bindToLife())
                .subscribe(new Consumer<List<NewsJokeCommentBean.DataBean.RecentCommentsBean>>() {
                    @Override
                    public void accept(@NonNull List<NewsJokeCommentBean.DataBean.RecentCommentsBean> recentCommentsBeen) throws Exception {
                        if (recentCommentsBeen.size() > 0) {
                            doSetAdapter(recentCommentsBeen);
                        } else {
                            doShowNoMore();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        doShowNetError();
                        CommErrorAction.print(throwable);
                    }
                });
    }

    @Override
    public void doLoadMoreData() {
        offset += 10;
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<NewsJokeCommentBean.DataBean.RecentCommentsBean> commentsBeanList) {
        commentsList.addAll(commentsBeanList);
        view.onSetAdapter(commentsList);
        view.onHideLoading();
    }

    @Override
    public void doRefresh() {
        if (commentsList.size() != 0) {
            commentsList.clear();
            offset = 0;
        }
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        if (commentsList.size() > 0) {
            view.onShowNoMore();
        }
    }
}
