package com.xun.smalltoutiao.appnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.xun.smalltoutiao.appnews.R;
import com.xun.smalltoutiao.appnews.bean.NewsJokeContentBean;
import com.xun.smalltoutiao.appnews.mvp.NewsIJokeComment;
import com.xun.smalltoutiao.appnews.mvp.NewsJokeCommentPresenter;
import com.xun.smalltoutiao.appnews.utils.NewsDiffCallback;
import com.xun.smalltoutiao.appnews.utils.NewsRegister;
import com.xun.smalltoutiao.libcomm.base.CommBaseListFragment;
import com.xun.smalltoutiao.libcomm.bean.CommLoadingBean;
import com.xun.smalltoutiao.libcomm.listeners.CommOnLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by xunwang on 2017/12/4.
 */

public class NewsJokeCommentFragment extends CommBaseListFragment<NewsIJokeComment.Presenter> implements NewsIJokeComment.View {
    public static final String TAG = "JokeCommentFragment";
    private String jokeId;
    private String jokeCommentCount;
    private String jokeText;
    private NewsJokeContentBean.DataBean.GroupBean jokeCommentHeaderBean;

    public static NewsJokeCommentFragment newInstance(Parcelable data) {
        Bundle args = new Bundle();
        args.putParcelable(TAG, data);
        NewsJokeCommentFragment fragment = new NewsJokeCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        initToolBar(toolbar, true, "");
//        toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                recyclerView.smoothScrollToPosition(0);
//            }
//        });

        adapter = new MultiTypeAdapter(oldItems);
        NewsRegister.registerJokeCommentItem(adapter);
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
        setHasOptionsMenu(true);
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.comm_fragment_list_toolbar;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        try {
            jokeCommentHeaderBean = bundle.getParcelable(TAG);
            jokeId = jokeCommentHeaderBean.getId() + "";
            jokeCommentCount = jokeCommentHeaderBean.getComment_count() + "";
            jokeText = jokeCommentHeaderBean.getText();
            oldItems.add(jokeCommentHeaderBean);
        } catch (Exception e) {

        }
        onLoadData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_joke_comment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_comment_share:
                Intent shareIntent = new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, jokeText);
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        presenter.doRefresh();
    }

    @Override
    public void onLoadData() {
        presenter.doLoadData(jokeId, jokeCommentCount);
    }

    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items();
        newItems.add(jokeCommentHeaderBean);
        newItems.addAll(list);
        newItems.add(new CommLoadingBean());
        NewsDiffCallback.notifyDataSetChanged(oldItems, newItems, NewsDiffCallback.JOKE_COMMENT, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    public void setPresenter(NewsIJokeComment.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsJokeCommentPresenter(this);
        }
    }

    @Override
    public void fetchData() {

    }
}
