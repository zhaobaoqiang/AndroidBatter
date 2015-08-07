package com.wudoumi.batter.view.loadview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.wudoumi.batter.R;
import com.wudoumi.batter.utils.ViewUtil;

/**
 * Created by Administrator on 2015/6/27.
 */
public class SimpleLoadView extends RelativeLayout implements ILoadAnimation{




    public SimpleLoadView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    protected void init(Context context){
        setBackgroundColor(Color.BLACK);
        LayoutInflater.from(context).inflate(R.layout.batter_loding,this,true);
    }
}
