package com.xun.smalltoutiao.appnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xun.smalltoutiao.appnews.R;

/**
 * Created by xunwang on 2017/11/19.
 */

public class NewsTabFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment_news_tab, container, false);
        return rootView;
    }
}
