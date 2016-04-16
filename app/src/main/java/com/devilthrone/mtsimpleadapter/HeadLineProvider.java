package com.devilthrone.mtsimpleadapter;

import android.content.Context;

import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;
import com.devilthrone.MTSimpleAdapter.viewholder.ViewHolder;


/**
 * Created by devilthrone on 16/4/3.
 */
public class HeadLineProvider implements ViewProvider<HeadLineBean> {
    @Override
    public void bindView(Context context, ViewHolder viewHolder, int position, HeadLineBean item) {
        viewHolder.setText(R.id.tv_title,item.title)
                  .setText(R.id.tv_msg,item.msg)
                  .setText(R.id.tv_time,item.time);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_headline_info;
    }
}
