package com.wudoumi.battertest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/6/12 0012.
 */
public class MyView extends View{
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TestTouch", "MyView onTouchEvent------>ACTION_DOWN");
                break;
                //return true;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestTouch","MyView onTouchEvent------>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestTouch","MyView onTouchEvent------>ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TestTouch","MyView onTouchEvent------>ACTION_CANCEL");
                break;
        }

//        boolean result = super.onTouchEvent(event);
//
//        Log.d("TestTouch","MyView onTouchEvent------>"+result);
        return true;


    }



}
