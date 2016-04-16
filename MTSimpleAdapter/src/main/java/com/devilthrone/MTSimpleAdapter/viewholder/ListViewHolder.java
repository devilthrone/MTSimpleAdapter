package com.devilthrone.MTSimpleAdapter.viewholder;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by devilthrone on 16/4/12.
 */
public class ListViewHolder implements ViewHolder {
    // 桥接模式适配AbsListView和RecyclerView
    ViewHolderBridge mHolder;
    private View mContentView;

    public ListViewHolder(Context context, ViewGroup parent, int layoutId) {
        mContentView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mContentView.setTag(this);
        mHolder = new ViewHolderBridge(mContentView);
    }

    /**
     * 获取ViewHolder
     * 封装了ViewHolder复用逻辑
     *
     * @param context     Context
     * @param convertView Item view
     * @param layoutId    布局资源id
     * @return
     */
    public static ListViewHolder getViewHolder(Context context, View convertView,
                                               ViewGroup parent, int layoutId) {

        context = (context == null && parent != null) ? parent.getContext() : context;
        ListViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ListViewHolder(context, parent, layoutId);
        } else {
            viewHolder = (ListViewHolder) convertView.getTag();
        }
        return viewHolder;
    }

    @Override
    /**
     * @return 当前项的convertView, 在构造函数中装载
     */
    public View getContentView() {
        return mContentView;
    }

    @Override
    public Context getContext() {
        return mHolder.mContentView.getContext();
    }


    @Override
    public ViewHolder setText(int viewId, int stringId) {
        mHolder.setText(viewId, stringId);
        return this;
    }

    @Override
    public ViewHolder setText(int viewId, String text) {
        mHolder.setText(viewId, text);
        return this;
    }

    @Override
    public ViewHolder setTextColor(int viewId, int color) {
        mHolder.setTextColor(viewId, color);
        return this;
    }

    @Override
    /**
     * @param viewId
     * @param color
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        mHolder.setBackgroundColor(viewId, color);
        return this;
    }

    @Override
    /**
     * @param viewId
     * @param resId
     */
    public ViewHolder setBackgroundResource(int viewId, int resId) {
        mHolder.setBackgroundResource(viewId, resId);
        return this;
    }


    @Override
    public ViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        mHolder.setBackgroundDrawable(viewId, drawable);
        return this;
    }

    @Override
    @TargetApi(16)
    public ViewHolder setBackground(int viewId, Drawable drawable) {
        mHolder.setBackground(viewId, drawable);
        return this;
    }

    @Override
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        mHolder.setImageBitmap(viewId, bitmap);
        return this;
    }

    @Override
    public ViewHolder setImageResource(int viewId, int resId) {
        mHolder.setImageResource(viewId, resId);
        return this;
    }

    @Override
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        mHolder.setImageDrawable(viewId, drawable);
        return this;
    }

    @Override
    public ViewHolder setImageUri(int viewId, Uri uri) {
        mHolder.setImageUri(viewId, uri);
        return this;
    }

    @Override
    @TargetApi(16)
    public ViewHolder setImageAlpha(int viewId, int alpha) {
        mHolder.setImageAlpha(viewId, alpha);
        return this;
    }

    @Override
    public ViewHolder setChecked(int viewId, boolean checked) {
        mHolder.setChecked(viewId, checked);
        return this;
    }

    @Override
    public ViewHolder setVisibility(int viewId, int visible) {
        mHolder.setVisibility(viewId, visible);
        return this;
    }

    @Override
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        mHolder.setOnClickListener(viewId, listener);
        return this;
    }

    @Override
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        mHolder.setOnTouchListener(viewId, listener);
        return this;
    }

    @Override
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        mHolder.setOnLongClickListener(viewId, listener);
        return this;
    }

    @Override
    public ViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        mHolder.setOnItemClickListener(viewId, listener);
        return this;
    }

    @Override
    public ViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        mHolder.setOnItemLongClickListener(viewId, listener);
        return this;
    }

    @Override
    public ViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        mHolder.setOnItemSelectedClickListener(viewId, listener);
        return this;
    }
}

