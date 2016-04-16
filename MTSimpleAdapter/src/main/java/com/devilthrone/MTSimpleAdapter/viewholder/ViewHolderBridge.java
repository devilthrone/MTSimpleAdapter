package com.devilthrone.MTSimpleAdapter.viewholder;

/**
 * Created by devilthrone on 16/1/16.
 */

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ViewHolder实现类，实现了View的常用基本设置操作
 *
 */
public class ViewHolderBridge {
    /**
     *
     */
     View mContentView;

    protected ViewHolderBridge(View contentView){
        mContentView = contentView;
    }

    /**
     * @return 当前项的convertView, 在构造函数中装载
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 为id为textViewId的TextView设置文本内容
     *
     * @param textViewId 视图id
     * @param text 要设置的文本内容
     */
    public void setText(int textViewId, CharSequence text) {
        TextView textView = ViewFinder.findViewById(mContentView, textViewId);
        if (textView != null) {
            textView.setText(text);
        }
    }
    public void setText(int viewId, int stringId) {
        TextView textView =ViewFinder.findViewById(mContentView, viewId);
        textView.setText(stringId);
    }
    public void setTextColor(int viewId ,int color){
        TextView textView =ViewFinder.findViewById(mContentView, viewId);
        textView.setTextColor(color);
    }

    public void setBackgroundColor(int viewId, int color) {
        View target = ViewFinder.findViewById(mContentView,viewId);
        target.setBackgroundColor(color);
    }
    public void setBackgroundResource(int viewId, int resId) {
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setBackgroundResource(resId);
    }
    public void setBackgroundDrawable(int viewId, Drawable drawable) {
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setBackgroundDrawable(drawable);
    }
    public void setBackground(int viewId, Drawable drawable){
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setBackground(drawable);
    }
    public void setImageBitmap(int viewId,Bitmap bitmap){
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setImageBitmap(bitmap);
    }
    public void setImageDrawable(int viewId , Drawable drawable){
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setImageDrawable(drawable);
    }
    public void setImageUri(int viewId , Uri uri){
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setImageURI(uri);
    }
    public void setImageAlpha(int viewId , int alpha){
        ImageView target = ViewFinder.findViewById(mContentView,viewId);
        target.setImageAlpha(alpha);
    }
    /**
     * 为ImageView设置图片
     *
     * @param imageViewId ImageView的id,
     * @param drawableId Drawable图片的id,
     */
    public void setImageResource(int imageViewId, int drawableId) {
        ImageView imageView = ViewFinder.findViewById(mContentView, imageViewId);
        if (imageView != null) {
            imageView.setImageResource(drawableId);
        }
    }

    /**
     * 为ImageView设置图片
     *
     * @param imageViewId ImageView的id, 例如R.id.my_imageview
     * @param bmp Bitmap图片
     */
    public void setImageResource(int imageViewId, Bitmap bmp) {
        ImageView imageView = ViewFinder.findViewById(mContentView, imageViewId);
        if (imageView != null) {
            imageView.setImageBitmap(bmp);
        }
    }

    /**
     * 为CheckBox设置是否选中
     *
     * @param checkViewId CheckBox的id
     * @param isCheck 是否选中
     */
    public void setChecked(int checkViewId, boolean isCheck) {
        CheckBox checkBox = ViewFinder.findViewById(mContentView, checkViewId);
        if (checkBox != null) {
            checkBox.setChecked(isCheck);
        }
    }

    /**
     * @param viewId
     * @param visibility
     */
    public void setVisibility(int viewId, int visibility) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    /**
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, OnClickListener listener) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }
    /**
     * @param viewId
     * @param listener
     */
    public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setOnLongClickListener(listener);
        }
    }

    /**
     * @param viewId
     * @param listener
     */
    public void setOnTouchListener(int viewId, OnTouchListener listener) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setOnTouchListener(listener);
        }
    }


    public void setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = ViewFinder.findViewById(mContentView,viewId);
        if (view != null) {
            view.setOnItemClickListener(listener);
        }
    }

    public void setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = ViewFinder.findViewById(mContentView,viewId);
        if (view != null) {
            view.setOnItemLongClickListener(listener);
        }
    }

    public void setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = ViewFinder.findViewById(mContentView,viewId);
        if (view != null) {
            view.setOnItemSelectedListener(listener);
        }
    }
}