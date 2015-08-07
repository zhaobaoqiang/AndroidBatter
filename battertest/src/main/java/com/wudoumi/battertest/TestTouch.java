package com.wudoumi.battertest;

import android.graphics.Color;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterActivity;


public class TestTouch extends BatterActivity {

    GridView mGridView;
    MAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mGridView = (GridView) findViewById(R.id.gridView);
        mAdapter = new MAdapter();
        mGridView.setAdapter(mAdapter);
    }

    int height = 100;
    class MAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 70;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if(convertView==null){
                tv = new TextView(TestTouch.this);
                tv.setBackgroundColor(Color.RED);
                convertView = tv;
            }else{
                tv = (TextView) convertView;
            }

            tv.setHeight(height);
            return convertView;
        }
    }


    private int mode = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                Log.d("TestTouch","Activity dispatchTouchEvent------>ACTION_DOWN");
                mode = 1;
                break;
                //return true;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestTouch","Activity dispatchTouchEvent------>ACTION_MOVE");
                //return true;
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestTouch","Activity dispatchTouchEvent------>ACTION_UP");
                mode = 0;
                break;
                //return true;

            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("TestTouch","Activity dispatchTouchEvent------>ACTION_POINTER_DOWN");
               mode++;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                Log.d("TestTouch","Activity dispatchTouchEvent------>ACTION_POINTER_UP");
                mode--;
                break;
        }

        if(mode==2){
            return onTouchEvent(ev);
        }else{
            boolean result = super.dispatchTouchEvent(ev);
            Log.d("TestTouch","Activity dispatchTouchEvent------>"+result);
            return result;
        }

        //return onTouchEvent(ev);

    }

    float oldDist = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                //mode = 1;
                Log.d("TestTouch","Activity onTouchEvent------>ACTION_DOWN");
                break;
                //return true;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestTouch","Activity onTouchEvent------>ACTION_MOVE");
                if (mode >= 2) {
                    float newDist = spacing(event);
                    if (newDist > oldDist + 10) {
                        //zoom(newDist / oldDist);
                        oldDist = newDist;

                        height += 20;
                        Log.e("TestTouch", "Activity onTouchEvent------>放大");

                        mAdapter.notifyDataSetChanged();
                    }
                    if (newDist < oldDist - 10) {
                       // zoom(newDist / oldDist);
                        oldDist = newDist;
                        height -= 20;
                        if(height<50){
                            height = 50;
                        }
                        mAdapter.notifyDataSetChanged();
                        Log.e("TestTouch","Activity onTouchEvent------>缩小");
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestTouch","Activity onTouchEvent------>ACTION_UP");
                //mode = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("TestTouch","Activity onTouchEvent------>ACTION_POINTER_DOWN");
                oldDist = spacing(event);
                //mode++;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("TestTouch","Activity onTouchEvent------>ACTION_POINTER_UP");
                //mode--;
                break;
        }
        //Log.d("TestTouch","Activity onTouchEvent------>result");
        //boolean result = super.onTouchEvent(event);
        //Log.d("TestTouch","Activity onTouchEvent------>"+result);
        return true;
    }


    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }
}

/**
 * 测试：
 *
 * 一：OnTouch始终返回fasle的情况：
 *
 * 1.dispatchTouchEvent 返回false时，事件未消费，传递给onTouch
 *   注意点：
 *
 *
 *  2.dispatchTouchEvent Down false,move true up false
 *
 *       Activity dispatchTouchEvent------>ACTION_DOWN
         Activity dispatchTouchEvent------>result
         Activity onTouchEvent------>ACTION_DOWN
         Activity onTouchEvent------>result
         Activity onTouchEvent------>false
         Activity dispatchTouchEvent------>false
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_MOVE
         Activity dispatchTouchEvent------>ACTION_UP
         Activity dispatchTouchEvent------>result
         Activity onTouchEvent------>ACTION_UP
         Activity onTouchEvent------>result
         Activity onTouchEvent------>false
         Activity dispatchTouchEvent------>false
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
