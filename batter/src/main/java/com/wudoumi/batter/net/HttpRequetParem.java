package com.wudoumi.batter.net;

import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;

import java.util.Map;

@SuppressWarnings("serial")
public class HttpRequetParem extends RequestParem {
	
	private final int requestType;
	private String url;
	
	

	public HttpRequetParem(int requestType, String url) {
		super();
		this.requestType = requestType;
		this.url = url;
	}

	@Override
	public int getRequestType() {
		// TODO Auto-generated method stub
		return requestType;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		if(requestType== RequestType.GET){
			if(size()!=0){
				url += "?";
				for(String key : keyList()){
					url += (key+"="+get(key));
				}
			}
		}
		return url;
	}

	@Override
	public final String getNameSpace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMapParem(Map<String,String> map) {
		for(String key:map.keySet()){
			put(key,map.get(key));
		}
	}


}
