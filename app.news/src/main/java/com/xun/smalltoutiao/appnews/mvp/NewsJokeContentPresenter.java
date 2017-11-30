package com.xun.smalltoutiao.appnews.mvp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.xun.smalltoutiao.appnews.api.NewsIJokeApi;
import com.xun.smalltoutiao.appnews.bean.NewsJokeContentBean;
import com.xun.smalltoutiao.libcomm.config.Constants;
import com.xun.smalltoutiao.libcomm.utils.CommErrorAction;
import com.xun.smalltoutiao.libcomm.utils.CommRetrofitFactory;
import com.xun.smalltoutiao.libcomm.utils.CommTimeUtil;
import com.xun.smalltoutiao.libcomm.utils.CommToutiaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xunwang on 2017/11/30.
 */

public class NewsJokeContentPresenter implements NewsIJokeContent.Presenter {
    private NewsIJokeContent.View view;
    private List<NewsJokeContentBean.DataBean.GroupBean> groupList = new ArrayList<>();
    private String time;

    public NewsJokeContentPresenter(NewsIJokeContent.View view) {
        this.view = view;
        this.time = CommTimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doLoadData() {

        Map<String, String> map = CommToutiaoUtil.getAsCp();

        CommRetrofitFactory.getRetrofit().create(NewsIJokeApi.class).getJokeContent(time, map.get(Constants.AS), map.get(Constants.CP))
                .subscribeOn(Schedulers.io())
                .map(new Function<NewsJokeContentBean, List<NewsJokeContentBean.DataBean.GroupBean>>() {
                    @Override
                    public List<NewsJokeContentBean.DataBean.GroupBean> apply(@NonNull NewsJokeContentBean jokeContentBean) throws Exception {
                        List<NewsJokeContentBean.DataBean> data = jokeContentBean.getData();
                        for (NewsJokeContentBean.DataBean dataBean : data) {
                            groupList.add(dataBean.getGroup());
                        }
                        return groupList;
                    }
                })
                .compose(view.<List<NewsJokeContentBean.DataBean.GroupBean>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NewsJokeContentBean.DataBean.GroupBean>>() {
                    @Override
                    public void accept(@NonNull List<NewsJokeContentBean.DataBean.GroupBean> groupBeen) throws Exception {
                        if (groupBeen.size() > 0) {
                            doSetAdapter();
                        } else {
                            doShowNoMore();
                        }
                    }
                }, CommErrorAction.error());
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doSetAdapter() {
        Log.d("kkkkkkkk", "doSetAdapter groupList --> " + groupList);
        view.onSetAdapter(groupList);
        view.onHideLoading();
    }

    @Override
    public void doRefresh() {
        if (groupList.size() != 0) {
            groupList.clear();
        }
        view.onShowLoading();
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
        view.onShowNoMore();
    }
}
