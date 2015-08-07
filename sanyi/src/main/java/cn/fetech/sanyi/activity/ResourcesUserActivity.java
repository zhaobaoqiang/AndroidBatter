package cn.fetech.sanyi.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.PagerSlidingTabStrip;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.fragment.OrderAnalysisFragment;
import cn.fetech.sanyi.fragment.ResourcesContactsFragment;
import cn.fetech.sanyi.fragment.ResourcesFollowUp;
import cn.fetech.sanyi.fragment.TestFragment;
import cn.fetech.sanyi.fragment.UserInfoFragemnt;
import cn.fetech.sanyi.fragment.ZhangtingFragment;

public class ResourcesUserActivity extends BaseActivity {

    private User user;

    @ViewInject(R.id.tab)
    private PagerSlidingTabStrip pst;


    @ViewInject(R.id.page)
    private ViewPager viewPager;


    @ViewInject(R.id.ib_expand)
    private ImageButton ibExpand;




    private Map<Integer,Fragment> map = new HashMap<>();



    private static final String[] TABS = new String[]{"跟进","联系人","展厅接待","赢丢单分析"};


    private UserInfoFragemnt userInfoFragemnt;

    @ViewInject(R.id.userinfo_content)
    private FrameLayout frameLayout;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_resources_user;
    }

    @Override
    protected String getToolBarTitle() {
        return user.getName();
    }

    @Override
    protected void initLocalData() {

        user = (User) getIntent().getSerializableExtra("User");
    }

    @Override
    protected void initView() {
        userInfoFragemnt = UserInfoFragemnt.instance(user);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.userinfo_content,userInfoFragemnt)
                .commit();

        viewPager.setAdapter(new TabPageAdapter());
        pst.setViewPager(viewPager);



    }

    public void expand(View view){
        if(ibExpand.isSelected()){
            ibExpand.setSelected(false);
        }else{
            ibExpand.setSelected(true);
        }

        if(frameLayout.getVisibility()==View.VISIBLE){
            frameLayout.setVisibility(View.GONE);
        }else{
            frameLayout.setVisibility(View.VISIBLE);
        }
    }



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
            return TABS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return TABS[position];
        }
    }


    private Fragment getFragment(int position){
        if(!map.containsKey(position)){
            switch (position){
                case 0:
                    map.put(position,ResourcesFollowUp.instance(user));
                    break;
                case 1:
                    map.put(position, ResourcesContactsFragment.instance(user));
                    break;
                case 2:
                    map.put(position, new ZhangtingFragment());
                    break;
                case 3:
                    map.put(position,OrderAnalysisFragment.instance(user));
                    break;
                default:
                    map.put(position, TestFragment.instance(TABS[position]));
                    break;
            }
        }
        return map.get(position);
    }





}
