package cn.fetech.sanyi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/6/24 0024 16:05.
 * qianjujun@163.com
 */
public class MainFirstFooterFragment extends BatterFragment{

    @ViewInject(R.id.radioGroup)
    private RadioGroup radioGroup;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_first_footer,null);
    }


    @Override
    protected void initData() {
        super.initData();

        showFragment(R.id.today_task);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                showFragment(i);
            }
        });

    }


    private void showFragment(int type){
        getChildFragmentManager().beginTransaction().replace(R.id.task_content,getFragment(type)).commit();
    }


    private Fragment getFragment(int type){

        if(!map.containsKey(type)){
            Fragment fragment = null;
            switch (type){
                case R.id.today_task:
                    fragment = MainFirestFooterTaskFragment.getInstance(true);
                    break;
                case R.id.week_task:
                    fragment = MainFirestFooterTaskFragment.getInstance(false);
                    break;
                default:
                    fragment = MainFirestFooterTaskFragment.getInstance(true);
                    break;
            }

            map.put(type,fragment);
        }

        return map.get(type);
    }

    private Map<Integer,Fragment> map = new HashMap<>();
}
