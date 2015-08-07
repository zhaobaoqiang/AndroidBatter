package com.wudoumi.batter.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.ioc.ViewUtils;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.loadview.EmptyLoadView;
import com.wudoumi.batter.view.loadview.Iretry;

/**
 * Created by Administrator on 2015/6/27.
 */
public abstract class BatterLoadingFragment extends Fragment{
    protected View cacheView;

    private EmptyLoadView emptyLoadView;

    private View laoutView;

    private BatterLoadResponseListner responseListner;


    private boolean isEmpty = true;



    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        onCreateViewStart();
        if(cacheView==null){
            cacheView = createView(inflater, container, savedInstanceState);
            initData();
            requestData(responseListner);
        }
        ViewGroup v = (ViewGroup) cacheView.getParent();
        if (v != null) {
            v.removeView(cacheView);
        }
        onCreateViewEnd();
        return cacheView;
    }

    private View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Context context = getActivity();
        RelativeLayout root = new RelativeLayout(context);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        emptyLoadView = new EmptyLoadView(context);
        emptyLoadView.setIretry(new Iretry() {
            @Override
            public void retryQuest() {
                requestData(responseListner);
            }
        });
        responseListner = new BatterLoadResponseListner(emptyLoadView){
            boolean success = false;
            @Override
            public void onStart() {
                super.onStart();
                success = false;
                if(isEmpty){
                    handlerEmptyRequesting();
                }else{
                    laoutView.setVisibility(View.VISIBLE);
                    closeEmptyLoading();
                }
            }

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                if(requestSuccess(result)){
                    success = true;
                    laoutView.setVisibility(View.VISIBLE);
                    isEmpty = false;
                    closeEmptyLoading();
                }else{
                    if(isEmpty){
                        handlerEmptyNoRequest();
                    }
                }
            }

            @Override
            public void onError(BatterExcetion error) {
                super.onError(error);
                success = false;

                requestError(error);
            }

            @Override
            public void onEnd() {
                super.onEnd();
                requestEnd(success);
            }
        };
        laoutView = initView(inflater, container, savedInstanceState);
        ViewUtils.inject(this, laoutView);
        root.addView(laoutView, rl);
        root.addView(emptyLoadView, rl);
        laoutView.setVisibility(View.GONE);
        return root;
    }


    /**
     * 请求的时候 设置空数据时的配置
     */
    protected void handlerEmptyRequesting(){
        laoutView.setVisibility(View.GONE);
        responseListner.openEmptyLoading();
    }


    /**
     * 请求成功后，人为清空数据 或者 请求成功后没有有效数据展示
     */
    protected final void handlerEmptyNoRequest(){
        isEmpty = true;
        handlerEmptyRequesting();
        emptyLoadView.setVisibility(View.VISIBLE);
    }

    protected abstract void requestData(BatterLoadResponseListner responseListner);



    /**
     * 保证生命周期函数可以回调
     */
    protected void onCreateViewStart(){

    }

    protected void onCreateViewEnd(){

    }

    protected  View initView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(getLayoutId(),null);
    }

    protected int getLayoutId(){
        return 0;
    }

    protected void initData(){

    }


    protected abstract boolean requestSuccess(String result);




    protected void requestEnd(boolean success){

    }

    protected void requestError(BatterExcetion e){

    }

    public BatterLoadResponseListner getResponseListner() {
        return responseListner;
    }
}
