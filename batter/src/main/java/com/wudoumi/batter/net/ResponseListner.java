package com.wudoumi.batter.net;


import com.wudoumi.batter.exception.BatterExcetion;

public abstract class ResponseListner {


	private boolean isCancel;
	
	public void onStart(){}

	public void onSuccess(String result){}

	public abstract void onError(BatterExcetion error);

	public void onEnd(){}
	
	public final void cancel(){
		isCancel = true;
	}
	
	public final boolean isCancel(){
		return isCancel;
	}


}
