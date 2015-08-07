package com.wudoumi.batter.exception;

@SuppressWarnings("serial")
public class BatterExcetion extends Exception{
	public BatterExcetion(String detail){
		super(detail);


	}
	
	
	
	public BatterExcetion(Throwable throwable){
		super(throwable);
	}
}
