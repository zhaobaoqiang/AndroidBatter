package com.wudoumi.battertest.test;

import android.os.Bundle;
import android.view.View;

import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.animation.shimmer.Shimmer;
import com.wudoumi.batter.view.animation.shimmer.ShimmerTextView;
import com.wudoumi.battertest.R;

/**
 * Created by Administrator on 2015/6/27.
 */
public class TestShimmer extends BatterActivity{
    @ViewInject(R.id.shimmer_text)
    ShimmerTextView stv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_shimmer);
    }

    public void btnClick(View view){
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(stv);
        }
    }
}
