package com.wudoumi.battertest;

import android.app.Application;

import com.wudoumi.batter.utils.AppUtil;
import com.wudoumi.batter.utils.PreferenceHelper;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.net.NetInterfaceFactory;

/**
 * Created by Administrator on 2015/6/11 0011.
 */
public class BatterApp extends Application{
    private static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        PreferenceHelper.init(this);
        DataBaseHelper.init(this);
        AppUtil.init(this);
    }



    public static NetInterface getNetInterface(){
        return NetInterfaceFactory.getInterface(app);
    }
}
