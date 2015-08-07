package com.wudoumi.batter.volley.toolbox;


import com.wudoumi.batter.volley.AuthFailureError;
import com.wudoumi.batter.volley.NetworkResponse;
import com.wudoumi.batter.volley.Request;
import com.wudoumi.batter.volley.Response;
import com.wudoumi.batter.volley.Response.ErrorListener;
import com.wudoumi.batter.volley.Response.Listener;

import java.util.Map;

public class SoapStringRequest extends Request<String>{
	private final Listener<String> mListener;
	private final RequestParem requestParem;
	public SoapStringRequest(RequestParem requestParem,Listener<String> listener,ErrorListener errorListener) {
		super(RequestType.SOAP, requestParem.getUrl(), errorListener);
		// TODO Auto-generated constructor stub
		this.mListener = listener;
		this.requestParem = requestParem;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub

		return Response.success(response.result, null);
	}

	@Override
	protected void deliverResponse(String response) {
		// TODO Auto-generated method stub
		mListener.onResponse(response);
	}
	
	
	@Override
	public Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return requestParem;
	}

}
