package com.xun.smalltoutiao.libcomm.binder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xun.smalltoutiao.libcomm.R;
import com.xun.smalltoutiao.libcomm.bean.CommLoadingBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by xunwang on 2017/11/30.
 */

public class CommLoadingViewBinder extends ItemViewBinder<CommLoadingBean, CommLoadingViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected CommLoadingViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.comm_item_loading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CommLoadingBean item) {
        int color = Color.RED;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(holder.progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, color);
            holder.progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            holder.progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            this.progressBar = itemView.findViewById(R.id.progress_footer);
        }
    }
}
