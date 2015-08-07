package com.wudoumi.batter.view.pullableview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wudoumi.batter.R;
import com.wudoumi.batter.utils.DateUtil;
import com.wudoumi.batter.utils.PreferenceHelper;
import com.wudoumi.batter.utils.StringUtil;

/**
 * Created by Administrator on 2015/6/16 0016.
 */
public class PullHeadView extends RelativeLayout{

    // 下拉的箭头
    private ImageView pullView;
    // 正在刷新的图标
    private ImageView refreshingView;

    //private boolean haveUpdateTime;

    private LinearLayout refreshingParent;
    private TextView tvFlushState,tvFlushDate;

    private LinearLayout refreshResultParent;
    // 刷新结果图标
    private ImageView refreshStateImageView;
    // 刷新结果：成功或失败
    private TextView refreshStateTextView;



    // 下拉箭头的转180°动画
    private RotateAnimation rotateAnimation;
    // 均匀旋转动画
    private RotateAnimation refreshingAnimation;


    private PreferenceHelper mPreferenceHelper;
    private DateUtil mDateUtil;


    public PullHeadView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context);
    }

    public PullHeadView(Context context) {
        super(context);
        init(context);
    }


    private void initView(Context context){
        LayoutInflater mInflater = LayoutInflater.from(context);

        View view = mInflater.inflate(R.layout.pull_head_02,this,true);

        pullView = (ImageView) view.findViewById(R.id.pull_icon);
        refreshingView = (ImageView) view.findViewById(R.id.refreshing_icon);


        refreshingParent = (LinearLayout) view.findViewById(R.id.state_flush_parent);
        tvFlushState = (TextView) findViewById(R.id.state_flush_tv);
        tvFlushDate = (TextView) findViewById(R.id.flush_time);

        refreshResultParent = (LinearLayout) view.findViewById(R.id.state_result_parent);
        refreshStateImageView = (ImageView) view.findViewById(R.id.state_iv);
        refreshStateTextView = (TextView) findViewById(R.id.state_result_tv);

    }

    private void initAnimation(Context context){
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.reverse_anim);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
        refreshingAnimation.setInterpolator(lir);
    }

    private void init(Context context){

        PreferenceHelper.init(context.getApplicationContext());
        mPreferenceHelper = PreferenceHelper.getInstance();
        mDateUtil = DateUtil.getInstance();
        setBackgroundColor(Color.BLACK);
        initView(context);
        initAnimation(context);
    }


    void changeState(int to){
        switch (to){
            case State.INIT:
                refreshingParent.setVisibility(View.VISIBLE);
                refreshResultParent.setVisibility(View.INVISIBLE);
                refreshStateImageView.setVisibility(View.INVISIBLE);
                tvFlushState.setText(R.string.pull_to_refresh);
                tvFlushDate.setText(getLastUpdateTime());
                pullView.clearAnimation();
                pullView.setVisibility(View.VISIBLE);
                break;
            case State.RELEASE_TO_REFRESH:
                // 释放刷新状态
                tvFlushState.setText(R.string.release_to_refresh);
                pullView.startAnimation(rotateAnimation);
                break;
            case State.REFRESHING:
                // 正在刷新状态
                pullView.clearAnimation();
                refreshingView.setVisibility(View.VISIBLE);
                pullView.setVisibility(View.INVISIBLE);
                refreshingView.startAnimation(refreshingAnimation);
                tvFlushState.setText(R.string.refreshing);
                break;
        }
    }


    void setUpdateTime(){
        tvFlushDate.setText(getLastUpdateTime());
    }

    void refreshFinish(int refreshResult)
    {
        refreshingView.clearAnimation();
        refreshingView.setVisibility(View.INVISIBLE);
        refreshingParent.setVisibility(View.INVISIBLE);
        refreshResultParent.setVisibility(View.VISIBLE);

        switch (refreshResult)
        {
            case State.SUCCEED:
                // 刷新成功

                refreshStateImageView.setVisibility(View.VISIBLE);
                refreshStateTextView.setText(R.string.refresh_succeed);
                refreshStateImageView.setImageResource(R.drawable.refresh_succeed);
                break;
            case State.FAIL:
            default:
                // 刷新失败
                refreshStateImageView.setVisibility(View.VISIBLE);
                refreshStateTextView.setText(R.string.refresh_fail);
                refreshStateImageView.setImageResource(R.drawable.refresh_failed);
                break;
        }

    }


    int getRefreshDist(){
        return getChildAt(0).getMeasuredHeight();
    }

    void clearPullViewAnim(){
        pullView.clearAnimation();
    }

    private String lastUpdateTime = "";
    private static final String UPDATE_TIME_KEY = "PULLVIEW_LAST_UPDATE_TIME";
    private String getLastUpdateTime(){
        String updateTime = "";
        if(StringUtil.getInstance().isEmpty(lastUpdateTime)){
            lastUpdateTime = mPreferenceHelper.getString(UPDATE_TIME_KEY,"");
        }
        if(StringUtil.getInstance().isEmpty(lastUpdateTime)){
            lastUpdateTime = mDateUtil.getHMS(System.currentTimeMillis());
        }
        updateTime = lastUpdateTime;
        lastUpdateTime = mDateUtil.getHMS(System.currentTimeMillis());
        mPreferenceHelper.put(UPDATE_TIME_KEY,lastUpdateTime);
        return updateTime;
    }



}
