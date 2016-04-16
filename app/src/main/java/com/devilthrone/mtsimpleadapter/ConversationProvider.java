package com.devilthrone.mtsimpleadapter;

import android.content.Context;

import com.devilthrone.MultiTemplateAdapter.provider.ViewProvider;
import com.devilthrone.MultiTemplateAdapter.viewholder.ViewHolder;


/**
 * Created by devilthrone on 16/4/3.
 */
public class ConversationProvider implements ViewProvider<ConversationBean> {
    @Override
    public void bindView(Context context, ViewHolder viewHolder, int position, ConversationBean item) {
            viewHolder.setText(R.id.tv_name,item.name)
                      .setText(R.id.tv_msg,item.msg)
                      .setText(R.id.tv_time,item.time);

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_conversation_info;
    }
}
