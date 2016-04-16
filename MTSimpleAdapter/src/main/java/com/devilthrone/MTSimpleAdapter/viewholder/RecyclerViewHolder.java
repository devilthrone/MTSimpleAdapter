package com.devilthrone.MTSimpleAdapter.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

/**
 * RecyclerViewHolder
 * Created by devilthrone on 16/4/12.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements ViewHolder {
    // 桥接模式适配AbsListView和RecyclerView
    ViewHolderBridge mHolder;


    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mHolder = new ViewHolderBridge( itemView ) ;
    }
    @Override
    public Context getContext() {
        return  mHolder.mContentView.getContext() ;
    }

    @Override
    public View getContentView() {
        return mHolder.getContentView();
    }

    @Override
    public ViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        mHolder.setBackgroundDrawable(viewId,drawable);
        return this;
    }

    @Override
    public ViewHolder setBackground(int viewId, Drawable drawable) {
        mHolder.setBackground(viewId,drawable);
        return this;
    }

    @Override
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        mHolder.setImageBitmap(viewId,bitmap);
        return this;
    }

    @Override
    public ViewHolder setImageResource(int viewId, int resId) {
        mHolder.setImageResource(viewId,resId);
        return this;
    }

    @Override
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        mHolder.setImageDrawable(viewId,drawable);
        return this;
    }

    @Override
    public ViewHolder setImageUri(int viewId, Uri uri) {
        mHolder.setImageUri(viewId,uri);
        return this;
    }

    @Override
    public ViewHolder setImageAlpha(int viewId, int alpha) {
        mHolder.setImageAlpha(viewId,alpha);
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
    @Override
    public ViewHolder setText(int viewId, int stringId) {
        mHolder.setText(viewId,stringId);
        return this;
    }

    @Override
    public ViewHolder setText(int viewId, String string) {
        mHolder.setText(viewId,string);
        return this;
    }

    @Override
    public RecyclerViewHolder setTextColor(int viewId, int color) {
        mHolder.setTextColor(viewId,color);
        return this;
    }

    @Override
    public RecyclerViewHolder setBackgroundColor(int viewId, int color) {
        mHolder.setBackgroundColor(viewId,color);
        return this;
    }

    @Override
    public RecyclerViewHolder setBackgroundResource(int viewId, int resId) {
        mHolder.setBackgroundResource(viewId,resId);
        return this;
    }
}
