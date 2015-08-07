package com.wudoumi.batter.view.loadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.wudoumi.batter.exception.BatterExcetion;

/**
 * Created by Administrator on 2015/6/27.
 */
public class EmptyLoadView extends RelativeLayout implements ILoadable{
    private View loadingView;

    private View errorView;

    private ILoadAnimation loadAnimation;

    private IError iError;

    private RelativeLayout.LayoutParams rl;

    private Iretry iretry;

    private boolean firstLoad = true;


    public void setIretry(Iretry iretry) {
        this.iretry = iretry;

    }

    public EmptyLoadView(Context context) {
        super(context);
        init();
    }

    public EmptyLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        rl = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    public void showLoading() {
        if(firstLoad){
            firstLoad = false;
        }

        setVisibility(View.VISIBLE);
        if(loadingView==null){
            loadingView = new SimpleLoadView(getContext());
            addView(loadingView,rl);
            loadAnimation = (ILoadAnimation) loadingView;
        }
        loadingView.setVisibility(View.VISIBLE);
        if(errorView!=null){
            errorView.setVisibility(View.GONE);
        }


        if(loadAnimation!=null){
            loadAnimation.start();
        }
    }

    @Override
    public void showFail(BatterExcetion error) {
        initErrorView(error);
    }


    private void initErrorView(BatterExcetion error){
        if(errorView==null){
            errorView = new SimpleErrorView(getContext());
            addView(errorView,rl);
            iError = (IError) errorView;
            iError.getClickAbleViewForRetry().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (iretry != null) {
                        iretry.retryQuest();
                    }
                }
            });
        }

        errorView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);

        if(loadAnimation!=null){
            loadAnimation.stop();
        }

        if(iError!=null){
            iError.handlerError(error);
        }


    }

    @Override
    public void showSuccees() {
        if(errorView!=null){
            errorView.setVisibility(View.VISIBLE);
        }
        loadingView.setVisibility(View.GONE);
        setVisibility(View.GONE);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(changedView==this&&visibility==View.VISIBLE&&errorView==null&&!firstLoad){
            initErrorView(new BatterExcetion("no data"));
        }
    }
}
