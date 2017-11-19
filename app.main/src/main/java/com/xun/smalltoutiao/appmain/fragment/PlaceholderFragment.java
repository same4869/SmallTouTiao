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

public class PlaceholderFragment extends Fragment {
    private static final String ARG_PLUGIN_NAME = "plugin_name";

    public static PlaceholderFragment newInstance(String pluginName) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLUGIN_NAME, pluginName);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.placeholder_tv);
        textView.setText(textView.getText().toString() + ":" + getArguments().getString(ARG_PLUGIN_NAME));
        return rootView;
    }
}
