package com.devilthrone.mtsimpleadapter;

import android.content.Context;

import com.devilthrone.MTSimpleAdapter.bean.IItemBean;
import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;
import com.devilthrone.MTSimpleAdapter.viewholder.ViewHolder;

/**
 * Created by devilthrone on 16/5/29.
 */
public class LoadingProvider implements ViewProvider {
    @Override
    public void bindView(Context context, ViewHolder viewHolder, int position, IItemBean item) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
