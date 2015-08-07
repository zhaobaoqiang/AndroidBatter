package cn.fetech.sanyi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.view.GaugeView;

/**
 * Created by qianjujun on 2015/6/24 0024 16:20.
 * qianjujun@163.com
 */
public class MainFirstHead2 extends BatterFragment{

    @ViewInject(R.id.gauge)
    GaugeView gaugeView;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_first_head2,null);
    }


    @Override
    protected void initData() {
        super.initData();
        gaugeView.setAngle(80);
        gaugeView.chartRender();
        gaugeView.invalidate();
    }
}
