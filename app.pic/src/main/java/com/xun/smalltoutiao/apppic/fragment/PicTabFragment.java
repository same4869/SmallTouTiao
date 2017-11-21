package com.xun.smalltoutiao.apppic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xun.smalltoutiao.apppic.R;

/**
 * Created by xunwang on 2017/11/20.
 */

public class PicTabFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pic_tab, container, false);
        return rootView;
    }
}
