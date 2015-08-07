package com.wudoumi.batter.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wudoumi.batter.ioc.ViewUtils;


public abstract class BatterFragment extends Fragment {
	
	
	protected View cacheView;

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		onCreateViewStart();
		if (cacheView == null) {
			cacheView = initView(inflater, container, savedInstanceState);
			ViewUtils.inject(this, cacheView);
			initData();
		}
		ViewGroup v = (ViewGroup) cacheView.getParent();
		if (v != null) {
			v.removeView(cacheView);
		}
		onCreateViewEnd();
		return cacheView;
	}
	
	
	/**
	 * 保证生命周期函数可以回调
	 */
	protected void onCreateViewStart(){

	}
	
	protected void onCreateViewEnd(){

	}
	



	protected  View initView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(getLayoutId(),null);
	}

	protected int getLayoutId(){
		return 0;
	}

	protected void initData(){}



}
