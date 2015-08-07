package com.wudoumi.battertest.view;

import android.view.View;

import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.net.ResponseListner;

/**
 * Created by Administrator on 2015/6/27.
 */
public class LoadingListner extends ResponseListner {

    private TestLoading loadView;
    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(BatterExcetion error) {

    }

    @Override
    public void onStart() {
        super.onStart();
        loadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onEnd() {
        super.onEnd();
    }
}
