package cn.fetech.sanyi.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.PagerSlidingTabStrip;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.GerenBaogaoFragment;
import cn.fetech.sanyi.fragment.TuanduiBaogaoFragment;

public class JobBaogaoActivity extends BaseActivity {

    @ViewInject(R.id.tab)
    private PagerSlidingTabStrip pst;


    @ViewInject(R.id.page)
    private ViewPager viewPager;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_job_baogao;
    }

    @Override
    protected String getToolBarTitle() {
        return "工作报告";
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new TabPageAdapter());
        pst.setViewPager(viewPager);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_job_baogao, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }




    class TabPageAdapter extends FragmentPagerAdapter {

        public TabPageAdapter() {

            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {

            return getFragment(position);
        }

        @Override
        public int getCount() {
            return currentTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return currentTitles[position];
        }
    }


    private Fragment getFragment(int position){
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new GerenBaogaoFragment();
                break;
            case 1:
                fragment = new TuanduiBaogaoFragment();
                break;
        }
        return fragment;
    }


    private static String[] currentTitles = new String[]{"个人","团队"};
}
