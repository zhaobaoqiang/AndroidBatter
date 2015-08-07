package com.wudoumi.batter.net;

import com.wudoumi.batter.volley.toolbox.RequestParem;

public interface NetInterface {
	void doRequest(RequestParem requestParem, ResponseListner reponse);


	<T> void doRequest(RequestParem requestParem, ResponseListner reponse,NetSuccessHandler<T> successHandler);

	void relase();
}
