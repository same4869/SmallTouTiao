package com.xun.smalltoutiao.libcomm.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xun.smalltoutiao.libcomm.R;
import com.xun.smalltoutiao.libcomm.bean.CommLoadingEndBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by xunwang on 2017/11/30.
 */

public class CommLoadingEndViewBinder extends ItemViewBinder<CommLoadingEndBean, CommLoadingEndViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected CommLoadingEndViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.comm_item_loading_end, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CommLoadingEndBean item) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
