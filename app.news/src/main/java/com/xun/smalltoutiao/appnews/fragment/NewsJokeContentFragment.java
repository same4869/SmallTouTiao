package com.xun.smalltoutiao.appnews.fragment;

import android.util.Log;
import android.view.View;

import com.xun.smalltoutiao.appnews.mvp.NewsIJokeContent;
import com.xun.smalltoutiao.appnews.mvp.NewsJokeContentPresenter;
import com.xun.smalltoutiao.appnews.utils.NewsDiffCallback;
import com.xun.smalltoutiao.appnews.utils.NewsRegister;
import com.xun.smalltoutiao.libcomm.base.CommBaseListFragment;
import com.xun.smalltoutiao.libcomm.bean.CommLoadingBean;
import com.xun.smalltoutiao.libcomm.listeners.CommOnLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by xunwang on 2017/11/30.
 */

public class NewsJokeContentFragment extends CommBaseListFragment<NewsIJokeContent.Presenter> implements NewsIJokeContent.View {

    public static NewsJokeContentFragment newInstance() {
        return new NewsJokeContentFragment();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Log.d("kkkkkkkk", "initView");
        adapter = new MultiTypeAdapter(oldItems);
        NewsRegister.registerJokeContentItem(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new CommOnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        Log.d("kkkkkkkk", "fetchData");
        super.fetchData();
        onLoadData();
    }

    @Override
    public void onLoadData() {
        Log.d("kkkkkkkk", "onLoadData");
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void setPresenter(NewsIJokeContent.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsJokeContentPresenter(this);
        }
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Log.d("kkkkkkkk", "onSetAdapter list --> " + list);
        Items newItems = new Items(list);
        newItems.add(new CommLoadingBean());
        NewsDiffCallback.notifyDataSetChanged(oldItems, newItems, NewsDiffCallback.JOKE, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    protected void initData() throws NullPointerException {
    }
}
