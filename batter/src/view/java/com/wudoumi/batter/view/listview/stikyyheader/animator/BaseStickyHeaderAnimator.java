package com.wudoumi.batter.view.listview.stikyyheader.animator;


import android.annotation.TargetApi;
import android.os.Build;

import com.wudoumi.batter.view.listview.stikyyheader.HeaderAnimator;

public class BaseStickyHeaderAnimator extends HeaderAnimator {

    private float mTranslationRatio;

    @Override
    protected void onAnimatorAttached() {
        //nothing to do
    }

    @Override
    protected void onAnimatorReady() {
        //nothing to do
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onScroll(int scrolledY) {

        getHeader().setTranslationY(Math.max(scrolledY, getMaxTranslation()));

        mTranslationRatio = calculateTranslationRatio(scrolledY);
    }

    public float getTranslationRatio() {
        return mTranslationRatio;
    }

    private float calculateTranslationRatio(int scrolledY) {
        //TODO check divisor != 0
        return (float) scrolledY / (float) getMaxTranslation();
    }

}
