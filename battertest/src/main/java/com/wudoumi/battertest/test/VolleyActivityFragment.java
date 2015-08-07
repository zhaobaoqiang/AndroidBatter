package com.wudoumi.battertest.test;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.HttpRequetParem;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.net.NetSuccessHandler;
import com.wudoumi.batter.net.SoapRequestParem;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;
import com.wudoumi.battertest.BatterApp;
import com.wudoumi.battertest.R;
import com.wudoumi.battertest.bean.News;
import com.wudoumi.battertest.bean.SuperListBean;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class VolleyActivityFragment extends BatterLoadingFragment {

    @ViewInject(R.id.text)
    TextView textView;

    private NetInterface mNetInterface;


    private int type = 1;


    //Random random = new Random();

    int maxnum = 2;

    static int currentNum = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNetInterface = BatterApp.getNetInterface();

        type = getArguments().getInt("type",1);
    }

    public VolleyActivityFragment() {

    }


    public static VolleyActivityFragment newInstance(int type){
        VolleyActivityFragment fragment = new VolleyActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        if(type==TYPE_SOAP){
            mNetInterface.doRequest(getParem(type), responseListner, new NetSuccessHandler<SuperListBean<News>>() {

                @Override
                public SuperListBean<News> convert(String json) {
                    return SuperListBean.getNewsBean(json);
                }

                @Override
                public boolean handlerResultInThread(SuperListBean<News> newsSuperListBean) {
                    Log.d("SuperListBean","thread:"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                        Log.d("SuperListBean","处理完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }

                @Override
                public void onUIReciverResult(SuperListBean<News> newsSuperListBean) {
                    Log.d("SuperListBean",newsSuperListBean.toString());
                }
            });
        }

    }




    @Override
    protected boolean requestSuccess(String result) {
        textView.setText(textView.getText()+result);
        return currentNum++>5;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_volley2;
    }




    public static final int TYPE_SOAP = 1;
    public static final int TYPE_POST = 2;
    public static final int TYPE_GET = 3;

    private static String soapUrl = "http://121.40.78.136/api.php?wsdl";
    private static String soapNameSpace = "urn:Mobile";

    public static RequestParem getParem(int type){
        RequestParem baseRequestParem = null;
        switch(type){
            case TYPE_SOAP:
                baseRequestParem = new SoapRequestParem("getInfos",soapUrl,soapNameSpace,false);
                baseRequestParem.put("code", "DSLZ1407");
                baseRequestParem.put("catId", "1");
                baseRequestParem.put("page", "1");
                baseRequestParem.put("nums", "10");
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
}
