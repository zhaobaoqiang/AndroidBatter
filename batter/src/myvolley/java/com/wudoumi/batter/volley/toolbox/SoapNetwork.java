package com.wudoumi.batter.volley.toolbox;

import com.wudoumi.batter.volley.Network;
import com.wudoumi.batter.volley.NetworkResponse;
import com.wudoumi.batter.volley.Request;
import com.wudoumi.batter.volley.VolleyError;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SoapNetwork implements Network {

	@Override
	public NetworkResponse performRequest(Request<?> request)
			throws VolleyError {
		// TODO Auto-generated method stub
		NetworkResponse mNetworkResponse = doCall((RequestParem) request
				.getParams());
		return mNetworkResponse;
	}

	private NetworkResponse doCall(RequestParem requestParem)
			throws VolleyError {
		try {
			SoapObject request = new SoapObject(requestParem.getNameSpace(),
					requestParem.getMethod());
			setparameters(request, requestParem);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE httpTransportSE = new HttpTransportSE(requestParem.getUrl(), 20000);
			httpTransportSE.debug = true;
			String soapAction = requestParem.useSoapAction()?requestParem.getNameSpace()+requestParem.getMethod():"";
			httpTransportSE.call(soapAction, envelope);
			Object object = envelope.bodyIn;
			if (object instanceof SoapObject) {
				Object bodyIn = envelope.getResponse();
				if (bodyIn != null) {
					return new NetworkResponse(bodyIn.toString());
				} else {
					throw new VolleyError("请求失败");
				}
			} else if (object instanceof SoapFault) {
				SoapFault fault = (SoapFault) envelope.bodyIn;
				if (fault != null) {
					// fault.getCause()
					throw new VolleyError(fault.getMessage(), fault.getCause());
				} else {
					throw new VolleyError("请求失败");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new VolleyError(e);
		}

		return new NetworkResponse("");
	}

	private void setparameters(SoapObject request, RequestParem requestParem) {
		for (String key : requestParem.keyList()) {
			request.addProperty(key, requestParem.get(key));
		}
	}

}
