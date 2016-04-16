package com.devilthrone.MTSimpleAdapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.devilthrone.MTSimpleAdapter.bean.IItemBean;
import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;
import com.devilthrone.MTSimpleAdapter.viewholder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 拥有多模板构造能力的极简adapter
 * 封装了onCreateViewHolder方法，
 * 封装了onBindViewHolder方法，
 * 封装了OnItemClick , OnItemLongClick等操作
 * 提供了add,remove,clear等操作，无需调用notifyDataSetChanged()
 * 当需要在RecyclerView 中实现多模板布局时，
 * 只需实现ViewProvider接口的bindView方法并返回布局即可
 * Created by devilthrone on 16/4/12.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context mContext;
    private List<IItemBean> mDataSet;
    private List<Class<? extends ViewProvider>> mProviders;
    private Map<String, ViewProvider> mCacheMap;
    private LayoutInflater mInflater;
    private ViewProvider mViewProvider;
    private SparseArray<Integer> layoutIdList;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    protected void setupItemClickListener(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    protected void setupItemLongClickListener(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(position);
                }
                return true;
            }
        });
    }

    /**
     * 点击事件Listener
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public RecyclerAdapter(Context context) {
        init(context, null);
    }


    public RecyclerAdapter(Context context, List<IItemBean> itemBeanList) {
        checkParams(context, itemBeanList);
        init(context, itemBeanList);
    }

    /**
     * 检查参数的有效性
     *
     * @param context
     * @param dataSource
     */
    private void checkParams(Context context, List<? extends IItemBean> dataSource) {
        if (context == null || dataSource == null) {
            throw new RuntimeException(
                    "context == null || dataSource == null, please check your params");
        }
    }

    public void init(Context context, List<IItemBean> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataSet = list;
        layoutIdList = new SparseArray<Integer>();
        mCacheMap = new HashMap<String, ViewProvider>();
        mProviders = new ArrayList<Class<? extends ViewProvider>>();
    }

    /**
     * 添加provider
     *
     * @param provider
     */
    public void addProvider(Class<? extends ViewProvider> provider) {
        if (!mProviders.contains(provider)) {
            mProviders.add(provider);
        }

    }

    /**
     * 移除provider和对应的数据
     *
     * @param provider
     */
    public void removeProviderAndData(Class<? extends ViewProvider> provider) {
        if (mProviders.contains(provider)) {
            mProviders.remove(provider);
            removeDataByProvider(provider);
            notifyDataSetChanged();
        }

    }

    public void removeDataByProvider(Class<? extends ViewProvider> provider) {
        if (mDataSet != null && mDataSet.size() > 0) {
            Iterator<IItemBean> iterator = mDataSet.iterator();
            while (iterator.hasNext()) {
                IItemBean bean = iterator.next();
                if (provider.getName().equals(bean.getViewProviderClass().getName())) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int itemLayoutId = layoutIdList.get(viewType);
        if(itemLayoutId < 0){
            throw  new IllegalArgumentException("itemLayoutId return not null");
        }
        return new RecyclerViewHolder(getView(parent, itemLayoutId));
    }

    /**
     * 解析布局资源
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View getView(ViewGroup viewGroup, int layoutId) {
        Context context = viewGroup.getContext();
        return LayoutInflater.from(context).inflate(layoutId,
                viewGroup, false);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        IItemBean itemBean = mDataSet.get(position);
        ViewProvider viewProvider = mCacheMap.get(itemBean.getViewProviderClass().getName());
        if(viewProvider!=null){
            viewProvider.bindView(mContext, holder, position, itemBean);
        }
        setupItemClickListener(holder, position);
        setupItemLongClickListener(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSet == null || position < 0 || position >= mDataSet.size()) {
            return 0;
        }
        IItemBean item = mDataSet.get(position);
        if (item.getViewProviderClass() == null) {
            throw new IllegalArgumentException("ItemBean implements method getViewProvider() return not null");
        }
        String viewProviderName = item.getViewProviderClass().getName();
        ViewProvider viewProvider = mCacheMap.get(viewProviderName);
        if(viewProvider != null){
            int index = mProviders.indexOf(viewProvider.getClass());
            layoutIdList.put(index, viewProvider.getLayoutId());
            return index;
        } else {
            int size = mProviders.size();
            for (int i = 0; i < size; i++) {
                if (viewProviderName.equals(mProviders.get(i).getName())) {
                    try {
                        viewProvider = item.getViewProviderClass().newInstance();
                        mCacheMap.put(viewProviderName, viewProvider);
                        layoutIdList.put(i, viewProvider.getLayoutId());
                        return i;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        if(viewProvider == null){
            throw new IllegalArgumentException(viewProviderName + " not add this provider");
        }

        return 0;
    }

    /**
     * 追加单个数据
     *
     * @param item
     */
    public void addItem(IItemBean item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }


    /**
     * 追加数据集
     *
     * @param list
     */
    public void addItems(List<IItemBean> list) {
        mDataSet.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据到列表头部
     *
     * @param item
     */
    public void addItemToHead(IItemBean item) {
        mDataSet.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * 添加数据集到列表头部
     *
     * @param list
     */
    public void addItemsToHead(List<IItemBean> list) {
        mDataSet.addAll(0, list);
        notifyDataSetChanged();
    }

    /**
     * 移除某个数据
     *
     * @param position
     */
    public void remove(int position) {
        mDataSet.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 移除某个数据项
     *
     * @param item
     */
    public void remove(IItemBean item) {
        mDataSet.remove(item);
        notifyDataSetChanged();
    }

    /**
     * clear all data
     */
    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取指定位置的数据项
     *
     * @param position
     * @return
     */
    public IItemBean getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
