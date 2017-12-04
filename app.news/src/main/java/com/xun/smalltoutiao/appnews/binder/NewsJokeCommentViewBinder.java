package com.xun.smalltoutiao.appnews.binder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xun.smalltoutiao.appnews.R;
import com.xun.smalltoutiao.appnews.bean.NewsJokeCommentBean;
import com.xun.smalltoutiao.libcomm.utils.CommErrorAction;
import com.xun.smalltoutiao.libcomm.utils.CommImageLoader;
import com.xun.smalltoutiao.libcomm.views.CommBottomSheetDialogFixed;
import com.xun.smalltoutiao.libcomm.views.CommCircleImageView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by xunwang on 2017/12/4.
 */

public class NewsJokeCommentViewBinder extends ItemViewBinder<NewsJokeCommentBean.DataBean.RecentCommentsBean, NewsJokeCommentViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected NewsJokeCommentViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.news_item_joke_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final NewsJokeCommentBean.DataBean.RecentCommentsBean item) {

        final Context context = holder.itemView.getContext();

        try {
            String iv_avatar = item.getUser_profile_image_url();
            String tv_username = item.getUser_name();
            String tv_text = item.getText();
            String tv_likes = item.getDigg_count() + "赞";

            CommImageLoader.loadCenterCrop(context, iv_avatar, holder.iv_avatar, R.color.viewBackground);
            holder.tv_username.setText(tv_username);
            holder.tv_text.setText(tv_text);
            holder.tv_likes.setText(tv_likes);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String content = item.getText();
                    final CommBottomSheetDialogFixed dialog = new CommBottomSheetDialogFixed(context);
//                    dialog.setOwnerActivity((BaseActivity) context);
//                    View view = ((BaseActivity) context).getLayoutInflater().inflate(R.layout.item_comment_action_sheet, null);
//                    view.findViewById(R.id.layout_copy_text).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//                            ClipData clipData = ClipData.newPlainText("text", content);
//                            copy.setPrimaryClip(clipData);
//                            Snackbar.make(holder.itemView, R.string.copied_to_clipboard, Snackbar.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                        }
//                    });
//                    view.findViewById(R.id.layout_share_text).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            IntentAction.send(context, content);
//                            dialog.dismiss();
//                        }
//                    });
//                    dialog.setContentView(view);
                    dialog.show();
                }
            });
        } catch (Exception e) {
            CommErrorAction.print(e);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CommCircleImageView iv_avatar;
        private TextView tv_username;
        private TextView tv_text;
        private TextView tv_likes;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_avatar = itemView.findViewById(R.id.iv_avatar);
            this.tv_username = itemView.findViewById(R.id.tv_username);
            this.tv_text = itemView.findViewById(R.id.tv_text);
            this.tv_likes = itemView.findViewById(R.id.tv_likes);
        }
    }
}
