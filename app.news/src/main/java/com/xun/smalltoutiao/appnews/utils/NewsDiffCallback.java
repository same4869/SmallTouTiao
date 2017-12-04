package com.xun.smalltoutiao.appnews.utils;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.xun.smalltoutiao.appnews.bean.NewsJokeCommentBean;
import com.xun.smalltoutiao.appnews.bean.NewsJokeContentBean;

import java.util.List;

/**
 * Created by xunwang on 2017/11/30.
 */

public class NewsDiffCallback extends DiffUtil.Callback {

    public static final int JOKE = 1;
    public static final int JOKE_COMMENT = 6;
    private List oldList, newList;
    private int type;

    public NewsDiffCallback(List oldList, List newList, int type) {
        this.oldList = oldList;
        this.newList = newList;
        this.type = type;
    }

    public static void notifyDataSetChanged(List oldList, List newList, int type, RecyclerView.Adapter adapter) {
        NewsDiffCallback diffCallback = new NewsDiffCallback(oldList, newList, type);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback, true);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        try {
            switch (type) {
                case JOKE:
                    return ((NewsJokeContentBean.DataBean.GroupBean) oldList.get(oldItemPosition)).getContent().equals(
                            ((NewsJokeContentBean.DataBean.GroupBean) newList.get(newItemPosition)).getContent());
                case JOKE_COMMENT:
                    return ((NewsJokeCommentBean.DataBean.RecentCommentsBean) oldList.get(oldItemPosition)).getText().equals(
                            ((NewsJokeCommentBean.DataBean.RecentCommentsBean) newList.get(newItemPosition)).getText());
            }
        } catch (Exception e) {
//            ErrorAction.print(e);
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        try {
            switch (type) {
                case JOKE:
                    return ((NewsJokeContentBean.DataBean.GroupBean) oldList.get(oldItemPosition)).getShare_url().equals(
                            ((NewsJokeContentBean.DataBean.GroupBean) newList.get(newItemPosition)).getShare_url());
                case JOKE_COMMENT:
                    return ((NewsJokeCommentBean.DataBean.RecentCommentsBean) oldList.get(oldItemPosition)).getId() ==
                            ((NewsJokeCommentBean.DataBean.RecentCommentsBean) newList.get(newItemPosition)).getId();
            }
        } catch (Exception e) {
//            ErrorAction.print(e);
        }
        return false;
    }
}
