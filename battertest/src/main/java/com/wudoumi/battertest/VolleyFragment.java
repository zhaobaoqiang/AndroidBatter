package com.wudoumi.battertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.HttpRequetParem;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.net.ResponseListner;
import com.wudoumi.batter.net.SoapRequestParem;
import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;

/**
 * Created by Administrator on 2015/6/11 0011.
 */


public class VolleyFragment extends BatterFragment implements View.OnClickListener{
    @ViewInject(R.id.post)
    private TextView post;
    @ViewInject(R.id.get)
    private TextView get;
    @ViewInject(R.id.soap)
    private TextView soap;


    private NetInterface mNetInterface;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volley, null);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNetInterface = BatterApp.getNetInterface();
    }

    @Override
    protected void onCreateViewEnd() {
        super.onCreateViewEnd();

        post.setOnClickListener(this);
        get.setOnClickListener(this);
        soap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        RequestParem mRequestParem = getParem(view.getId());
        if(view instanceof TextView){
            request((TextView) view,mRequestParem);
        }

    }
    public static final int TYPE_SOAP = R.id.soap;
    public static final int TYPE_POST = R.id.post;
    public static final int TYPE_GET = R.id.get;

    private static String soapUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
    private static String soapNameSpace = "http://WebXml.com.cn/";

    public static RequestParem getParem(int type){
        RequestParem baseRequestParem = null;
        switch(type){
            case TYPE_SOAP:
                baseRequestParem = new SoapRequestParem("getWeatherbyCityName",soapUrl,soapNameSpace,true);
                baseRequestParem.put("theCityName", "上海");
                //baseRequestParem.put("password", "kukids");
                break;
            case TYPE_POST:
                baseRequestParem = new HttpRequetParem(RequestType.POST,"http://api.k780.com:88/");
                baseRequestParem.put("app", "weather.today");
                baseRequestParem.put("weaid", "1");
                baseRequestParem.put("appkey", "10003");
                baseRequestParem.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
                baseRequestParem.put("format", "json");
                break;
            case TYPE_GET:
                baseRequestParem = new HttpRequetParem(RequestType.GET,"http://www.baidu.com");
                break;
        }
        return baseRequestParem;
    }



    private void request(final TextView tv,RequestParem parem){
        mNetInterface.doRequest(parem, new ResponseListner() {
            @Override
            public void onSuccess(String result) {
                tv.setText(result);
            }

            @Override
            public void onError(BatterExcetion error) {
                tv.setText(error.getMessage());
            }


            @Override
            public void onStart() {
                super.onStart();
                tv.setText("请求中");
                tv.setClickable(false);
            }

            @Override
            public void onEnd() {
                super.onEnd();
                tv.setClickable(true);
            }
        });
    }

}
