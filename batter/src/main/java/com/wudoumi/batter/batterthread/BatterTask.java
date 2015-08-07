package com.wudoumi.batter.batterthread;

import android.os.AsyncTask;


import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.net.ResponseListner;


/**
 * Created by qianjujun on 2015/6/30 0030 13:19.
 * qianjujun@163.com
 */
public class BatterTask extends AsyncTask<Void,Void,Exception> {


    private final BatterTaskItem batterTaskItem;

    private final ResponseListner responseListner;

    private boolean handlerSuccess;

    public BatterTask(BatterTaskItem batterTaskItem) {
        super();
        this.batterTaskItem = batterTaskItem;
        this.responseListner = batterTaskItem.getResponseListner();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!responseListner.isCancel()){
            responseListner.onStart();
        }

    }

    @Override
    protected Exception doInBackground(Void... voids) {

        try {
            if(!responseListner.isCancel()){
                handlerSuccess = batterTaskItem.getDoWork().doWorkInThread();
            }
        }catch (Exception e){
            return e;
        }
        return null;
    }


    @Override
    protected void onPostExecute(Exception e) {
        super.onPostExecute(e);

        if(e==null){
            if(!responseListner.isCancel()){
                batterTaskItem.getResponseListner().onSuccess(handlerSuccess+"");
            }
        }else{
            if(!responseListner.isCancel()){
                batterTaskItem.getResponseListner().onError(new BatterExcetion(e));
            }
        }
        if(!responseListner.isCancel()){
            batterTaskItem.getResponseListner().onEnd();
        }

    }
}
