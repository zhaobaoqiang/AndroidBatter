package com.wudoumi.batter.batterthread;

import com.wudoumi.batter.net.ResponseListner;

/**
 * Created by qianjujun on 2015/6/30 0030 13:15.
 * qianjujun@163.com
 */
public class BatterTaskItem {
    private int position;

    private final ResponseListner responseListner;

    private final DoWork doWork;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public BatterTaskItem(ResponseListner responseListner, DoWork doWork) {
        this.responseListner = responseListner;
        this.doWork = doWork;
    }


    public ResponseListner getResponseListner() {
        return responseListner;
    }

    public DoWork getDoWork() {
        return doWork;
    }
}
