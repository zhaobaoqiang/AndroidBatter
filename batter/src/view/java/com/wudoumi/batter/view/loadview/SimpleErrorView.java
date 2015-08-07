package com.wudoumi.batter.view.loadview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wudoumi.batter.R;
import com.wudoumi.batter.exception.BatterExcetion;

/**
 * Created by Administrator on 2015/6/27.
 */
public class SimpleErrorView extends RelativeLayout implements IError{


    protected View handlerErrorView;

    private View clickAbleView;

    public SimpleErrorView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void handlerError(BatterExcetion excetion) {
        if(handlerErrorView instanceof TextView){
            ((TextView)handlerErrorView).setText(getResources().getString(R.string.batter_error)+excetion.getMessage());
        }
    }

    @Override
    public View getClickAbleViewForRetry() {
        return clickAbleView;
    }


    protected void init(Context context){
        setBackgroundColor(Color.BLACK);

        View view = LayoutInflater.from(context).inflate(R.layout.batter_error,this,true);

        clickAbleView = view.findViewById(R.id.batter_retry);

        handlerErrorView = view.findViewById(R.id.tv_batter_error_info);

    }



}
