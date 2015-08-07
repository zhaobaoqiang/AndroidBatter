package cn.fetech.sanyi;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.bean.UserSerach;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.fragment.MainFirstWithHeadFragment;
import cn.fetech.sanyi.fragment.MainTxlFragment;
import cn.fetech.sanyi.fragment.MainUserFragment;
import cn.fetech.sanyi.fragment.TestFragment;


/**
 * A simple {@link BatterFragment} subclass.
 *
 *
 * 废弃
 */
public class MainFragmentNoScroll extends BatterFragment {

    @ViewInject(R.id.radioGroup)
    private RadioGroup mRadioGroup;


    private Fragment shouyeFragment,ziyuanFragment,kehuFragment,txlFragment;


    private Map<Integer,Fragment> map = new HashMap<>();


    private FragmentManager fragmentManager;

    public MainFragmentNoScroll() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_fragment_no_scroll;
    }


    @Override
    protected void initData() {
        super.initData();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                showFragment(checkId);
            }
        });

        //第一次加载初始化三个页面，首界面快捷进入联系人界面，并设置搜索条件时
        initFragment();


        showFragment(R.id.radio_first);
    }


    private void initFragment(){
        FragmentTransaction f = fragmentManager.beginTransaction();
        shouyeFragment = new MainFirstWithHeadFragment();
        ziyuanFragment = MainUserFragment.getInstance(MainUserFragment.TYPE_RESOURCES);
        kehuFragment = MainUserFragment.getInstance(MainUserFragment.TYPE_COUSTOM);
        txlFragment = new MainTxlFragment();

        f.add(R.id.content,shouyeFragment);
        map.put(R.id.radio_first,shouyeFragment);

        f.add(R.id.content,ziyuanFragment);
        map.put(R.id.radio_resources,ziyuanFragment);

        f.add(R.id.content,kehuFragment);
        map.put(R.id.radio_customer,kehuFragment);

        f.add(R.id.content,txlFragment);
        map.put(R.id.radio_txl,txlFragment);


        //f.show(shouyeFragment);
        f.commit();
    }




    private void showFragment(int checkId){
        Fragment f = getFragment(checkId);
        for(Fragment fragment : map.values()){
            if(fragment==f){
                fragmentManager.beginTransaction().show(fragment).commit();
            }else{
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }

    private Fragment getFragment(int checkId){

        return map.get(checkId);
    }


    class SearchBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            UserSerach userSerach = (UserSerach) intent.getSerializableExtra("userSerach");
            if(userSerach!=null){
                int position = userSerach.getUserType()== User.TYPE_USER_FORMAL?R.id.radio_customer:R.id.radio_resources;


               // showFragment(position);

                ((RadioButton)mRadioGroup.findViewById(position)).setChecked(true);




                ((MainUserFragment)getFragment(position)).doSerachFormOut(userSerach);
            }
        }
    }


    private SearchBroadCast searchBroadCast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBroadCast = new SearchBroadCast();
        fragmentManager = getChildFragmentManager();
    }


    @Override
    protected void onCreateViewStart() {
        super.onCreateViewStart();
        IntentFilter intentFilter = new IntentFilter(Constant.BROADCAST_SEARCH);
        getActivity().registerReceiver(searchBroadCast,intentFilter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(searchBroadCast);
    }
}
