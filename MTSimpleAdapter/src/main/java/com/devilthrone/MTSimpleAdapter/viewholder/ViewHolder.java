package com.devilthrone.MTSimpleAdapter.viewholder;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

/**
 * ViewHolder接口，适配RecyclerAdapter和ListAdapter
 * 实现了常用的View操作方法
 * Created by devilthrone on 16/4/12.
 */
public interface ViewHolder {

    Context getContext();

    View getContentView();

    ViewHolder setText(int viewId, int stringId);

    ViewHolder setText(int viewId, String string);

    ViewHolder setTextColor(int viewId, int color);

    ViewHolder setBackgroundColor(int viewId, int color);

    ViewHolder setBackgroundResource(int viewId, int resId);

    ViewHolder setBackgroundDrawable(int viewId, Drawable drawable);

    @TargetApi(16)
    ViewHolder setBackground(int viewId, Drawable drawable);

    ViewHolder setImageBitmap(int viewId, Bitmap bitmap);

    ViewHolder setImageResource(int viewId, int resId);

    ViewHolder setImageDrawable(int viewId, Drawable drawable);

    ViewHolder setImageUri(int viewId, Uri uri);

    @TargetApi(16)
    ViewHolder setImageAlpha(int viewId, int alpha);

    ViewHolder setChecked(int viewId, boolean checked);

    ViewHolder setVisibility(int viewId, int visible);

    ViewHolder setOnClickListener(int viewId, View.OnClickListener listener);

    ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener);

    ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener);

    ViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener);

    ViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener);

    ViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener);
}
