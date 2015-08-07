package com.wudoumi.battertest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.net.ResponseListner;

/**
 * Created by qianjujun on 2015/6/26 0026 13:49.
 * qianjujun@163.com
 */
public class TestLoading extends RelativeLayout{
    public TestLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    private View Loding;

    private View Fail;



    public class LoadingListner extends ResponseListner{

        @Override
        public void onSuccess(String result) {

        }

        @Override
        public void onError(BatterExcetion error) {

        }

        @Override
        public void onStart() {
            super.onStart();
            Fail.setVisibility(View.GONE);
        }


        @Override
        public void onEnd() {
            super.onEnd();
        }
    }






}
