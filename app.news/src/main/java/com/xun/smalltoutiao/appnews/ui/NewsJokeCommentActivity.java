package com.xun.smalltoutiao.appnews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xun.smalltoutiao.appnews.R;
import com.xun.smalltoutiao.appnews.bean.NewsJokeContentBean;
import com.xun.smalltoutiao.appnews.fragment.NewsJokeCommentFragment;
import com.xun.smalltoutiao.libcomm.base.CommBaseActivity;

/**
 * Created by xunwang on 2017/12/4.
 */

public class NewsJokeCommentActivity extends CommBaseActivity {
    private static final String TAG = "NewsCommentView";

    public static void launch(NewsJokeContentBean.DataBean.GroupBean bean) {
        CommBaseActivity.getAppContext().startActivity(new Intent(CommBaseActivity.getAppContext(), NewsJokeCommentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, NewsJokeCommentFragment.newInstance(getIntent().getParcelableExtra(TAG)))
                .commit();
    }
}
