package cn.fetech.sanyi.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.adapter.MainfirstHeadFragmentAdapter;

/**
 * Created by qianjujun on 2015/6/24 0024 16:04.
 * qianjujun@163.com
 */
public class MainFirstHeadFragment extends BatterFragment {

    @ViewInject(R.id.viewPager)
    private ViewPager mViewPager;

    @ViewInject(R.id.radioGroup)
    private RadioGroup mRadioGroup;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_first_head, null);
    }


    @Override
    protected void initData() {
        super.initData();
        mViewPager.setAdapter(new MainfirstHeadFragmentAdapter(getChildFragmentManager()));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) mRadioGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
