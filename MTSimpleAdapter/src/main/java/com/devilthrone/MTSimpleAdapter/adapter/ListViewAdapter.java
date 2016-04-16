package com.devilthrone.MTSimpleAdapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.devilthrone.MTSimpleAdapter.bean.IItemBean;
import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;
import com.devilthrone.MTSimpleAdapter.viewholder.ListViewHolder;
import com.devilthrone.MTSimpleAdapter.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by devilthrone on 16/4/3.
 * 拥有多模板构造能力的极简adapter，
 * 封装了convertView缓存复用操作，
 * 封装了ViewHolder加载操作，
 * 提供了add,remove,clear等操作，无需调用notifyDataSetChanged()
 * 当需要在ListView 或 GridView中实现多模板布局时，
 * 只需实现ViewProvider接口的bindView方法并返回布局即可
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<IItemBean> mDataSet;
    private List<Class<? extends ViewProvider>> mProviders ;
    private Map<String , ViewProvider> mCacheMap;
    private LayoutInflater mInflater;
    private ViewProvider viewProvider;
    public ListViewAdapter(Context context){
        init(context,null);
    }


    public ListViewAdapter(Context context, List<IItemBean> dataSource) {
        checkParams(context, dataSource);
        init(context, dataSource);
    }
    /**
     * 检查参数的有效性
     *
     * @param context
     * @param dataSource
     */
    private void checkParams(Context context, List<? extends IItemBean> dataSource) {
        if (context == null  || dataSource == null) {
            throw new RuntimeException(
                    "context == null || dataSource == null, please check your params");
        }
    }
    public void init(Context context, List<IItemBean> dataSource) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        if(dataSource == null){
            dataSource = new ArrayList<IItemBean>();
        }
        mDataSet = dataSource;
        mCacheMap = new HashMap<String , ViewProvider>();
        mProviders = new ArrayList<Class<? extends ViewProvider>>();
    }

    /**
     * 添加provider
     * @param provider
     */
    public void addProvider(Class<? extends ViewProvider> provider){
        if(!mProviders.contains(provider)){
            mProviders.add(provider);
        }

    }

    /**
     * 移除provider和对应的数据
     * @param provider
     */
    public void removeProviderAndData(Class<? extends ViewProvider> provider){
        if(mProviders.contains(provider)){
            mProviders.remove(provider);
            removeDataByProvider(provider);
            notifyDataSetChanged();
        }

    }
    public void removeDataByProvider(Class<? extends ViewProvider> provider){
        if(mDataSet !=null && mDataSet.size()>0){
            Iterator<IItemBean> iterator = mDataSet.iterator();
            while(iterator.hasNext()){
                IItemBean bean = iterator.next();
                if(provider.getName().equals(bean.getViewProviderClass().getName())) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (mProviders == null || mProviders.size() < 1) {
            throw new IllegalArgumentException("providers must not null or size < 1");
        }
        IItemBean itemBean = mDataSet.get(position);

        if (itemBean.getViewProviderClass() == null) {
            throw new IllegalArgumentException(itemBean + " getViewProviderClass() return not null");
        }
        String viewProviderName = itemBean.getViewProviderClass().getName();
        //先从缓存中获取
        ViewProvider viewProvider = mCacheMap.get(viewProviderName);
        if (viewProvider == null) {
            int size = mProviders.size();
            for (int i = 0; i < size; i++) {
                if (viewProviderName.equals(mProviders.get(i).getName())) {
                    try {
                        viewProvider = itemBean.getViewProviderClass().newInstance();
                        mCacheMap.put(viewProviderName,viewProvider);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        if (viewProvider == null) {
            throw new IllegalArgumentException(viewProviderName + " not add this provider");
        }
        int itemLayoutId  = viewProvider.getLayoutId();
        if(itemLayoutId < 0){
            throw  new IllegalArgumentException("getLayoutId() return not null");
        }
        ViewHolder viewHolder = ListViewHolder.getViewHolder(mContext, convertView, parent,
                itemLayoutId);
        viewProvider.bindView(mContext,viewHolder, position, itemBean);
        return viewHolder.getContentView();
    }

    public ViewProvider getViewProvider() {
        return viewProvider;
    }

    public List<? extends IItemBean> getDataSet() {
        return mDataSet;
    }

    public void setDataSet(List<IItemBean> list) {
        if (list == null) {
            return;
        }
        mDataSet = list;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public IItemBean getItem(int position) {
        if (mDataSet != null && position < mDataSet.size() && position >= 0) {
            return mDataSet.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mDataSet == null ? 0 : mDataSet.size();
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


    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSet == null || position < 0 || position >= mDataSet.size()) {
            return 0;
        }
        IItemBean item = mDataSet.get(position);
        if (item.getViewProviderClass() == null) {
            throw new IllegalArgumentException("IItemBean implements method getViewProvider() return not null");
        }
        Class providerClass = item.getViewProviderClass();
        int size = mProviders.size();
        for (int i = 0; i < size; i++) {
            if (providerClass.getName().equals(mProviders.get(i).getName())) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        int typeSize = mProviders.size();
        return typeSize <= 0 ? 1 : typeSize;
    }

}