package com.wudoumi.batter.view.loadview;

import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.net.ResponseListner;

/**
 * Created by Administrator on 2015/6/27.
 */
public class BatterLoadResponseListner extends ResponseListner{

    protected ILoadable loadable;

    protected boolean showEmptyLoad = true;

    public BatterLoadResponseListner(ILoadable loadable) {
        this.loadable = loadable;
    }

    @Override
    public void onSuccess(String result) {
        if(showEmptyLoad){
            loadable.showSuccees();
        }
    }

    @Override
      public void onError(BatterExcetion error) {
        if(showEmptyLoad){
            loadable.showFail(error);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if(showEmptyLoad){
            loadable.showLoading();
        }
    }

    @Override
    public void onEnd() {

        super.onEnd();
    }


    public void closeEmptyLoading() {
        this.showEmptyLoad = false;
        loadable.showSuccees();
    }

    public void openEmptyLoading(){

        this.showEmptyLoad = true;
    }



}
