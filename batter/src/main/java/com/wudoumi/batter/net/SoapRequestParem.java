package com.wudoumi.batter.net;

import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;

import java.util.Map;

@SuppressWarnings("serial")
public class SoapRequestParem extends RequestParem {
	
	
	private final String method;
	
	private final String url;
	private final String nameSpace;
	
	
	private boolean useSoapAction;

	public SoapRequestParem(String method, String url, String nameSpace) {
		this(method,url,nameSpace,false);
	}


	public SoapRequestParem(String method, String url, String nameSpace,boolean useSoapAction) {
		super();
		this.method = method;
		this.url = url;
		this.nameSpace = nameSpace;
		this.useSoapAction = useSoapAction;
	}
	

	@Override
	public int getRequestType() {
		// TODO Auto-generated method stub
		return RequestType.SOAP;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return nameSpace;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return method;
	}

	/**
	 * (soap协议参数有序)
	 */
	@Deprecated
	@Override
	public final void setMapParem(Map<String,String> map) {

	}

	@Override
	public boolean useSoapAction() {
		return useSoapAction;
	}

	public void setUseSoapAction(boolean useSoapAction) {
		this.useSoapAction = useSoapAction;
	}
}
