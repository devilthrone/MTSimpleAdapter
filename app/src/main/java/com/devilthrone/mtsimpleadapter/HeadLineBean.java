package com.devilthrone.mtsimpleadapter;


import com.devilthrone.MTSimpleAdapter.bean.IItemBean;
import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;

/**
 * Created by devilthrone on 16/4/3.
 */
public class HeadLineBean implements IItemBean {
    public String title;
    public String msg;
    public String time;

    public HeadLineBean(  String title,String time,String msg) {
        this.msg = msg;
        this.time = time;
        this.title = title;
    }

    @Override
    public String toString() {
        return "HeadLineBean{" +
                "title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public Class<? extends ViewProvider> getViewProviderClass() {
        return HeadLineProvider.class;
    }
}
