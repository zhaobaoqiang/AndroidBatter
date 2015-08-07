package com.wudoumi.battertest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;


import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.PagerSlidingTabStrip;


public class MainActivity extends BatterActivity {

    @ViewInject(R.id.tab)
    private PagerSlidingTabStrip mPagerSlidingTabStrip;

    @ViewInject(R.id.page)
    private ViewPager mViewPager;


    private String[] titles = new String[]{"下载","图片","网络","数据库"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //ListView

        mViewPager.setAdapter(new TabPageAdapter(getSupportFragmentManager()));
        mPagerSlidingTabStrip.setViewPager(mViewPager);

    }



    class TabPageAdapter extends FragmentPagerAdapter{

        public TabPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    private Fragment getFragment(int position){
        Fragment fragment = null;
        if(position==2){
            fragment = new VolleyFragment();
        }else if(position==3){
            fragment = new DbFragment();
        }else{
            fragment = new VolleyFragment();
        }
        return fragment;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
