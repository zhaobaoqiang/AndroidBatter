package com.wudoumi.batter.view.loadview;

import com.wudoumi.batter.exception.BatterExcetion;

/**
 * Created by Administrator on 2015/6/27.
 */
public interface ILoadable {

    void showLoading();

    void showFail(BatterExcetion error);

    void showSuccees();
}
