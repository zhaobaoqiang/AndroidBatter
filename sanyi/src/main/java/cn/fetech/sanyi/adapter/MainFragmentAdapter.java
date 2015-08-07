package cn.fetech.sanyi.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.data.FragmentUtil;
import cn.fetech.sanyi.fragment.MainFirstWithHeadFragment;
import cn.fetech.sanyi.fragment.MainTxlFragment;
import cn.fetech.sanyi.fragment.MainUserFragment;


/**
 * Created by qianjujun on 2015/6/24 0024 12:52.
 * qianjujun@163.com
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {


    public MainFragmentAdapter(FragmentManager fm) {
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
                    mFragment = new MainFirstWithHeadFragment();
                    break;
                case 1:
                    mFragment = MainUserFragment.getInstance(MainUserFragment.TYPE_RESOURCES);
                    //mFragment = FragmentUtil.getMainUserFragment(MainUserFragment.TYPE_RESOURCES);
                    break;
                case 2:
                    mFragment = MainUserFragment.getInstance(MainUserFragment.TYPE_COUSTOM);
                    //mFragment = FragmentUtil.getMainUserFragment(MainUserFragment.TYPE_COUSTOM);
                    break;
                case 3:
                    mFragment = new MainTxlFragment();
                    break;

            }
            map.put(position,mFragment);
        }
        return map.get(position);
    }



}
