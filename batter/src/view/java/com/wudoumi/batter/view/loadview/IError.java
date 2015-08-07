package com.wudoumi.batter.view.loadview;

import android.view.View;

import com.wudoumi.batter.exception.BatterExcetion;

/**
 * Created by Administrator on 2015/6/27.
 */
public interface IError{
    void handlerError(BatterExcetion excetion);

    View getClickAbleViewForRetry();
}
