package com.wudoumi.battertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class TestMyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_view);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TestTouch", "TestMyViewActivity onTouchEvent------>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestTouch","TestMyViewActivity onTouchEvent------>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestTouch","TestMyViewActivity onTouchEvent------>ACTION_UP");
                break;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
