package cn.fetech.sanyi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.adapter.TaskAdapter;
import cn.fetech.sanyi.bean.Task;
import cn.fetech.sanyi.data.TestData;

/**
 * Created by qianjujun on 2015/6/25 0025 10:52.
 * qianjujun@163.com
 */
public class MainFirestFooterTaskFragment extends BatterFragment{
    @ViewInject(R.id.listview)
    private ListView mListView;

    private List<Task> tasks = new ArrayList<>();

    private TaskAdapter taskAdapter;

    private boolean today;


    private FragmentManager mFragmentManager;

    private Fragment headFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        today = getArguments().getBoolean("today");
        mFragmentManager = getParentFragment().getParentFragment().getChildFragmentManager();
        headFragment = mFragmentManager.findFragmentById(R.id.headFragment);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_main_first_footer_task,null);

    }


    @Override
    protected void initData() {
        super.initData();
        tasks.addAll(TestData.createTestTasks());
        if(!today){
            tasks.addAll(TestData.createTestTasks());
            tasks.addAll(TestData.createTestTasks());
        }

        taskAdapter = new TaskAdapter(tasks,getActivity());
        mListView.setAdapter(taskAdapter);


        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean up = false;
            int startItem = 0;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if(i==SCROLL_STATE_FLING){
                    startItem = absListView.getFirstVisiblePosition();
                }else if(i==SCROLL_STATE_IDLE){
                    up = startItem<=absListView.getFirstVisiblePosition();

                    if(up){
                        if(headFragment.isVisible()){
                            mFragmentManager.beginTransaction().hide(headFragment).commit();
                        }

                    }else{
                        if(headFragment.isHidden()&&absListView.getFirstVisiblePosition()==0){
                            mFragmentManager.beginTransaction().show(headFragment).commit();
                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }


    public static MainFirestFooterTaskFragment getInstance(boolean today){
        MainFirestFooterTaskFragment fragment = new MainFirestFooterTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("today",today);
        fragment.setArguments(bundle);
        return fragment;
    }




}
