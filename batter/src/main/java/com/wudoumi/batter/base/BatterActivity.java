package com.wudoumi.batter.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wudoumi.batter.utils.ScreenManager;
import com.wudoumi.batter.ioc.ViewUtils;

/**
 * Created by Administrator on 2015/6/9 0009.
 */
public class BatterActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ScreenManager.getScreenManager().popActivity(this);
    }


    @Override
    public void setContentView(int layoutResID) {
        // TODO Auto-generated method stub
        super.setContentView(layoutResID);
        ViewUtils.inject(this);

    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        // TODO Auto-generated method stub
        super.setContentView(view, params);
        ViewUtils.inject(this);
    }

    @Override
    public void setContentView(View view) {
        // TODO Auto-generated method stub
        super.setContentView(view);
        ViewUtils.inject(this);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
