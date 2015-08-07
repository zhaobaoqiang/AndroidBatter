package cn.fetech.sanyi.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.fragment.MainFirstHead1;
import cn.fetech.sanyi.fragment.MainFirstHead2;
import cn.fetech.sanyi.fragment.MainFirstHead3;
import cn.fetech.sanyi.fragment.MainFirstHead4;
import cn.fetech.sanyi.fragment.TestFragment;


/**
 * Created by qianjujun on 2015/6/24 0024 12:52.
 * qianjujun@163.com
 */
public class MainfirstHeadFragmentAdapter extends FragmentPagerAdapter {


    public MainfirstHeadFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return getFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    private Map<Integer,Fragment> map = new HashMap<>();

    private Fragment getFragment(int position){
        if(!map.containsKey(position)){
            Fragment mFragment = null;
            switch (position) {
                case 0:
                    mFragment = new MainFirstHead1();
                    break;
                case 1:
                    //mFragment = TestFragment.instance("订单管理");
                    mFragment = new MainFirstHead2();
                    break;
                case 2:
                    mFragment = TestFragment.instance("合同管理");
                    break;
                case 3:
                    mFragment = TestFragment.instance("展厅管理");
                    break;

            }
            map.put(position,mFragment);
        }
        return map.get(position);
    }



}
