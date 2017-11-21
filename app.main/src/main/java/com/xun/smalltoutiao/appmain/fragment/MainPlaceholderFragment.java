package com.xun.smalltoutiao.appmain.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xun.smalltoutiao.appmain.R;

/**
 * Created by xunwang on 2017/11/17.
 */

public class MainPlaceholderFragment extends Fragment {
    private static final String ARG_PLUGIN_NAME = "plugin_name";

    public static MainPlaceholderFragment newInstance(String pluginName) {
        MainPlaceholderFragment fragment = new MainPlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLUGIN_NAME, pluginName);
        fragment.setArguments(args);
        return fragment;
    }

    public MainPlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_placeholder, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.placeholder_tv);
        textView.setText(textView.getText().toString() + ":" + getArguments().getString(ARG_PLUGIN_NAME));
        return rootView;
    }
}
