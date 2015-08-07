package com.wudoumi.battertest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/6/12 0012.
 */
public class MyViewGroup extends RelativeLayout {
    public MyViewGroup(Context context, AttributeSet attrs) {

        super(context, attrs);

        //requestDisallowInterceptTouchEvent(true);
    }


    int i = 0;


    /**
     * 小结：onInterceptTouchEvent 一旦拦截之后将把事件传递给自己的onTouch，之后将不在回调自己
     *
     *
     * @param ev
     * @return
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                Log.d("TestTouch", "MyViewGroup onInterceptTouchEvent------>ACTION_DOWN");

                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestTouch","MyViewGroup onInterceptTouchEvent------>ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestTouch","MyViewGroup onInterceptTouchEvent------>ACTION_UP");
                break;
        }
        return false;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TestTouch", "MyViewGroup onTouchEvent------>ACTION_DOWN");
                break;
                //return true;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestTouch","MyViewGroup onTouchEvent------>ACTION_MOVE");
                break;
                //return false;
            case MotionEvent.ACTION_UP:
                Log.d("TestTouch","MyViewGroup onTouchEvent------>ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }


        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //Log.d("TestTouch", "MyViewGroup dispatchTouchEvent------>ACTION_DOWN");

                i = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d("TestTouch","MyViewGroup dispatchTouchEvent------>ACTION_MOVE");

                if(i++<=2){
                    return onTouchEvent(ev);
                }
                break;
            case MotionEvent.ACTION_UP:
                //Log.d("TestTouch","MyViewGroup dispatchTouchEvent------>ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }



}
