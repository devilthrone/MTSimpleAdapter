package com.devilthrone.MTSimpleAdapter.bean;


import com.devilthrone.MTSimpleAdapter.provider.ViewProvider;

/**
 *  Created by devilthrone on 2016/04/02.
 *  ItemBean接口
 *  所有ListView中的item需实现此接口 返回所在ViewProvider的class
 */
public interface IItemBean {
	/**
	 * 返回所在provider的class
	 * @return
     */
	public abstract Class<? extends ViewProvider> getViewProviderClass();
}
