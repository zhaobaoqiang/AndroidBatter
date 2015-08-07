package com.wudoumi.battertest.view;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.wudoumi.batter.view.PagerSlidingTabStrip;
import com.wudoumi.battertest.R;

/**
 * Created by qianjujun on 2015/7/9 0009 12:50.
 * qianjujun@163.com
 */
public class ViewPagerHeader extends FrameLayout{



    int headHight = 400;

    int tabHight = 100;

    View viewHead;
    ViewPager viewPager;

    PagerSlidingTabStrip pst;

    private int mTouchSlop;




    public ViewPagerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout,this,true);
        viewHead = view.findViewById(R.id.head);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        pst = (PagerSlidingTabStrip) view.findViewById(R.id.tab);

        headHightPx = 0;
        pstHightPx = headHightPx+headHight;

        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //super.onLayout(changed, left, top, right, bottom);

        Log.d("ViewPagerHeader","headHightPx:"+headHightPx+"pstHightPx:"+pstHightPx);
        viewHead.layout(0,headHightPx,viewHead.getMeasuredWidth(),headHight);
        pst.layout(0,pstHightPx,pst.getMeasuredWidth(),pstHightPx+tabHight);
        viewPager.layout(0,pstHightPx+tabHight,viewPager.getMeasuredWidth(),viewPager.getMeasuredHeight());

    }
    private int headHightPx = 0;
    private int pstHightPx = 0;

    private float lastX;
    private float lastY;


    private boolean first = true;
    private boolean hor = true;

    float distanceX = 0;
    float distanceY = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = ev.getRawX();
                lastY = ev.getRawY();
                //dispatchTouchEvent(ev);
                return true;
            case MotionEvent.ACTION_MOVE:

                Log.d("ViewPagerHeader","startMove");
                distanceX = ev.getRawX() - lastX;
                distanceY = ev.getRawY() - lastY;

                lastX += distanceX;
                lastY += distanceY;

//                if(headHightPx+headHight<=0){
//                    headHightPx = -headHight;
//                    return super.dispatchTouchEvent(ev);
//                }

                headHightPx += distanceY;
                if(headHightPx+headHight<=0){
                    headHightPx = -headHight;
                }
                pstHightPx += distanceY;
                if(pstHightPx<=0){
                    pstHightPx = 0;
                }

                if(headHightPx>=0){
                    headHightPx = 0;
                }

                if(pstHightPx>=headHight){
                    pstHightPx = headHight;
                }

                requestLayout();
                Log.d("ViewPagerHeader","endMove");
                return true;

        }
        return super.dispatchTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
