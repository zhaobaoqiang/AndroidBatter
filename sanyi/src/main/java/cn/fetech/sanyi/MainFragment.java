package cn.fetech.sanyi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.adapter.MainFragmentAdapter;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.bean.UserSerach;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.fragment.MainUserFragment;

/**
 * Created by qianjujun on 2015/6/24 0024 10:25.
 * qianjujun@163.com
 */
public class MainFragment extends BatterFragment {
    @ViewInject(R.id.viewPager)
    private ViewPager mViewPager;

    @ViewInject(R.id.radioGroup)
    private RadioGroup mRadioGroup;

    private static Map<Integer,Integer> map = new HashMap<>();

    private MainFragmentAdapter mainFragmentAdapter;

    static {
        map.put(R.id.radio_first,0);
        map.put(R.id.radio_resources,1);
        map.put(R.id.radio_customer,2);
        map.put(R.id.radio_txl,3);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    protected void initData() {
        mainFragmentAdapter = new MainFragmentAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mainFragmentAdapter);

        mViewPager.setOffscreenPageLimit(3);

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

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
               mViewPager.setCurrentItem(map.get(checkId));
            }
        });

    }

    /**
     * 广播处理
     */
    class SearchBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            UserSerach userSerach = (UserSerach) intent.getSerializableExtra("userSerach");
            if(userSerach!=null){
                int position = userSerach.getUserType()== User.TYPE_USER_FORMAL?2:1;

                mViewPager.setCurrentItem(position);

                ((MainUserFragment)mainFragmentAdapter.getItem(position)).doSerachFormOut(userSerach);
            }
        }
    }


    private SearchBroadCast searchBroadCast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBroadCast = new SearchBroadCast();
    }

    /**
     * 注册广播
     */
    @Override
    protected void onCreateViewEnd() {
        super.onCreateViewStart();
        IntentFilter intentFilter = new IntentFilter(Constant.BROADCAST_SEARCH);
        getActivity().registerReceiver(searchBroadCast,intentFilter);
    }


    /**
     * 反注册广播
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(searchBroadCast);
    }
}
