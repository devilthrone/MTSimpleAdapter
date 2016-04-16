package com.devilthrone.MTSimpleAdapter.viewholder;


import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * View查找工具类，无需强制转换，返回泛型
 * Created by devilthrone on 16/4/02.
 */
public final class ViewFinder {

    /**
     *
     */
    public static boolean DEBUG = false;

    /**
     * 每项的View的sub view Map
     */
    private static SparseArray<WeakReference<View>> mViewMap = new SparseArray<WeakReference<View>>();
    /**
     * Root View的弱引用,
     * 不会阻止View对象被释放。如果该mRootView没有被外部引用，那么在重新设置了rootView之后老的rootView会被释放.
     */
    private static WeakReference<View> mRootView;

    /**
     * 设置Content View
     *
     * @param contentView 页面的Content View
     */
    public static void initContentView(View contentView) {

        if (contentView == null) {
            throw new RuntimeException(
                    "ViewFinder init failed, contentView == null.");
        }

        //
        mRootView = new WeakReference<View>(contentView);
        // 每次清除缓存的view id
        mViewMap.clear();

    }

    /**
     * 初始化ViewFinder
     *
     * @param context
     * @param layoutId
     */
    public static void initContentView(Context context, int layoutId) {
        initContentView(context, null, layoutId);
    }

    /**
     * @param context
     * @param parent
     * @param layoutId 布局id
     */
    public static void initContentView(Context context, ViewGroup parent, int layoutId) {
        if (context == null || layoutId <= 0) {
            throw new RuntimeException(
                    "initContentView invalid params, context == null || layoutId <= 0.");
        }
        View rootView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        initContentView(rootView);
    }

    /**
     * 返回顶级视图
     *
     * @return
     */
    public static View getContentView() {
        return mRootView.get();
    }

    /**
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(int viewId) {

        // 先从view map中查找,如果有的缓存的话直接使用,否则再从mContentView中找
        View targetView = null;
        WeakReference<View> viewWrf = mViewMap.get(viewId);
        if (viewWrf != null) {
            targetView = viewWrf.get();
        }

        if (targetView == null && mRootView != null && mRootView.get() != null) {
            targetView = mRootView.get().findViewById(viewId);
            mViewMap.put(viewId, new WeakReference<View>(targetView));
        }

        Log.d("", "### find view = " + targetView);
        return targetView == null ? null : (T) targetView;
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(View rootView, int viewId) {

        // 先从view map中查找,如果有的缓存的话直接使用,否则再从contentView中找
        View targetView = null;
        if (rootView != null) {
            targetView = rootView.findViewById(viewId);
        }
        Log.d("", "### find view = " + targetView);
        return targetView == null ? null : (T) targetView;
    }

    /**
     * 清理Views
     */
    public static void clear() {
        if (mRootView != null) {
            mRootView.clear();
            mRootView = null;
        }

        if (mViewMap != null) {
            mViewMap.clear();
            mViewMap = null;
        }
    }
}