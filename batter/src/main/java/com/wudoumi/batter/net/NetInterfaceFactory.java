package com.wudoumi.batter.net;

import android.app.Application;

public class NetInterfaceFactory {

	public static NetInterface getInterface(Application app){
		NetInterface netInterface = VolleyNet.getInstance(app);
		return netInterface;
	}
	
	public static NetInterface getInterface(){
		NetInterface netInterface = VolleyNet.getInstance();
		return netInterface;
	}
	
}
