package com.wudoumi.batter.view.pullableview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wudoumi.batter.R;

/**
 * Created by Administrator on 2015/6/17 0017.
 */
public class PullDownFooterView extends RelativeLayout {
    // 上拉的箭头
    private ImageView pullUpView;
    // 正在加载的图标
    private ImageView loadingView;
    // 加载结果图标
    private ImageView loadStateImageView;
    // 加载结果：成功或失败
    private TextView loadStateTextView;


    // 下拉箭头的转180°动画
    private RotateAnimation rotateAnimation;
    // 均匀旋转动画
    private RotateAnimation refreshingAnimation;

    public PullDownFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullDownFooterView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.BLACK);
        initView(context);
        initAnimation(context);
    }


    private void initView(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);

        View loadmoreView = mInflater.inflate(R.layout.pull_footer, this,true);
        pullUpView = (ImageView) loadmoreView.findViewById(R.id.pullup_icon);
        loadStateTextView = (TextView) loadmoreView.findViewById(R.id.loadstate_tv);
        loadingView = (ImageView) loadmoreView.findViewById(R.id.loading_icon);
        loadStateImageView = (ImageView) loadmoreView.findViewById(R.id.loadstate_iv);
    }

    private void initAnimation(Context context) {
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.reverse_anim);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
        refreshingAnimation.setInterpolator(lir);
    }


    void loadmoreFinish(int refreshResult) {
        loadingView.clearAnimation();
        loadingView.setVisibility(View.GONE);
        switch (refreshResult) {
            case State.SUCCEED:
                // 加载成功
                loadStateImageView.setVisibility(View.VISIBLE);
                loadStateTextView.setText(R.string.load_succeed);
                loadStateImageView.setBackgroundResource(R.drawable.load_succeed);
                break;
            case State.FAIL:
            default:
                // 加载失败
                loadStateImageView.setVisibility(View.VISIBLE);
                loadStateTextView.setText(R.string.load_fail);
                loadStateImageView.setBackgroundResource(R.drawable.load_failed);
                break;
        }
    }


    void changeState(int to) {
        switch (to) {
            case State.INIT:
                // 下拉布局初始状态

                // 上拉布局初始状态
                loadStateImageView.setVisibility(View.GONE);
                loadStateTextView.setText(R.string.pullup_to_load);
                pullUpView.clearAnimation();
                pullUpView.setVisibility(View.VISIBLE);
                break;


            case State.RELEASE_TO_LOAD:
                // 释放加载状态
                loadStateTextView.setText(R.string.release_to_load);
                pullUpView.startAnimation(rotateAnimation);
                break;
            case State.LOADING:
                // 正在加载状态
                pullUpView.clearAnimation();
                loadingView.setVisibility(View.VISIBLE);
                pullUpView.setVisibility(View.INVISIBLE);
                loadingView.startAnimation(refreshingAnimation);
                loadStateTextView.setText(R.string.loading);
                break;
        }
    }

    void clearPullDownAnimation(){
        pullUpView.clearAnimation();
    }

    int getLoadmoreDist(){
        return getChildAt(0).getMeasuredHeight();
    }


}
