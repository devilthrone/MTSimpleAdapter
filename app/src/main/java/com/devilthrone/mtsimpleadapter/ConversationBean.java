package com.devilthrone.mtsimpleadapter;


import com.devilthrone.MTSimpleAdapter.bean.IItemBean;
import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;

/**
 * Created by devilthrone on 16/4/3.
 */
public class ConversationBean implements IItemBean {
    public String name;
    public String msg;
    public String time;

    public ConversationBean(String name, String msg, String time) {
        this.name = name;
        this.msg = msg;
        this.time = time;
    }

    @Override
    public String toString() {
        return "ConversationBean{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public Class<? extends ViewProvider> getViewProviderClass() {
        return ConversationProvider.class;
    }
}
