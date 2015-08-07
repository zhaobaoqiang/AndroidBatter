package com.wudoumi.batter.net;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.app.Application;

import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.volley.AuthFailureError;
import com.wudoumi.batter.volley.NetworkResponse;
import com.wudoumi.batter.volley.Request;
import com.wudoumi.batter.volley.RequestQueue;
import com.wudoumi.batter.volley.Response;
import com.wudoumi.batter.volley.VolleyError;
import com.wudoumi.batter.volley.toolbox.HttpHeaderParser;
import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;
import com.wudoumi.batter.volley.toolbox.Volley;


public class VolleyNet implements NetInterface {
	
	private static VolleyNet instance = null;
	private RequestQueue requestQueue;
	private VolleyNet(Application app){
		requestQueue = Volley.newRequestQueue(app);
	}
	
	public static VolleyNet getInstance(Application app){
		if(instance==null){
			synchronized (VolleyNet.class) {
				if(instance==null){
					instance = new VolleyNet(app);
				}
			}
		}
		return instance;
	}
	
	
	public static VolleyNet getInstance(){
		if(instance==null){
			throw new RuntimeException("没有NetInterface");
		}
		return instance;
	}
	

	@Override
	public void doRequest(RequestParem requestParem,
			final ResponseListner reponse) {
		// TODO Auto-generated method stub
		reponse.onStart();

		StringRequest stringRequest = new StringRequest(requestParem, reponse);
		
		requestQueue.add(stringRequest);
	}

	@Override
	public <T> void doRequest(RequestParem requestParem, ResponseListner reponse, NetSuccessHandler<T> successHandler) {
		reponse.onStart();

		StringRequest<T> stringRequest = new StringRequest<>(requestParem,reponse,successHandler);

		requestQueue.add(stringRequest);
	}

	class StringRequest<T> extends Request<String> {
		ResponseListner mResponseListner;
		RequestParem requestParem;
		NetSuccessHandler<T> successHandler;

		T t = null;
		public StringRequest(RequestParem requestParem,
				final ResponseListner mResponseListner) {
			this(requestParem,mResponseListner,null);
		}
		public StringRequest(RequestParem requestParem,
							 final ResponseListner mResponseListner,NetSuccessHandler<T> successHandler) {
			super(requestParem.getRequestType(), requestParem.getUrl(),
					new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub
							mResponseListner.onError(new BatterExcetion(error));
							mResponseListner.onEnd();
						}
					});
			// TODO Auto-generated constructor stub
			this.mResponseListner = mResponseListner;
			this.requestParem = requestParem;
			this.successHandler = successHandler;
		}

		@Override
		protected Response<String> parseNetworkResponse(NetworkResponse response) {
			// TODO Auto-generated method stub

			if (requestParem.getRequestType() == RequestType.SOAP) {
				return Response.success(response.result, null);
			} else {
				String parsed;
				try {
					parsed = new String(response.data,
							HttpHeaderParser.parseCharset(response.headers));
				} catch (UnsupportedEncodingException e) {
					parsed = new String(response.data);
				}
				return Response.success(parsed,
						HttpHeaderParser.parseCacheHeaders(response));
			}

		}

		@Override
		protected void deliverResponse(String response) {
			// TODO Auto-generated method stub
			if(successHandler==null){
				mResponseListner.onSuccess(response);
				mResponseListner.onEnd();
			}else{
				handlerSuccess(mResponseListner,response,successHandler);
			}

		}


		private void handlerSuccess(final ResponseListner netRl,final String result,final NetSuccessHandler<T> successHandler){


			ResponseListner rl = new ResponseListner() {
				@Override
				public void onError(BatterExcetion error) {
					netRl.onError(error);
				}

				@Override
				public void onSuccess(String result) {

					successHandler.onUIReciverResult(t);

					netRl.onSuccess("请在后续处理的回调函数里处理");
				}

				@Override
				public void onEnd() {
					super.onEnd();
					netRl.onEnd();
				}
			};

			DoWork doWork = new DoWork() {

				@Override
				public boolean doWorkInThread() {
					t = successHandler.convert(result);
					return successHandler.handlerResultInThread(t);
				}
			};
			BatterTaskItem batterTaskItem = new BatterTaskItem(rl,doWork);
			new BatterTask(batterTaskItem).execute();
		}

		@Override
		public boolean isCanceled() {
			// TODO Auto-generated method stub
			return super.isCanceled() || mResponseListner.isCancel();
		}

		@Override
		public Map<String, String> getParams() throws AuthFailureError {
			// TODO Auto-generated method stub
			return requestParem;
		}

	}

	@Override
	public void relase() {
		// TODO Auto-generated method stub
		if(requestQueue!=null){
			requestQueue.cancelAll(new RequestQueue.RequestFilter() {
				
				@Override
				public boolean apply(Request<?> request) {
					// TODO Auto-generated method stub
					return true;
				}
			});

			requestQueue.stop();
			
			requestQueue=null;
		}
		
		if(instance!=null){
			instance = null;
		}
	}

}
