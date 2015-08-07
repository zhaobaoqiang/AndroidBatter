package cn.fetech.sanyi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wudoumi.batter.base.BatterFragment;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/6/24 0024 16:20.
 * qianjujun@163.com
 */
public class MainFirstHead4 extends BatterFragment{
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_customer,null);
    }
}
