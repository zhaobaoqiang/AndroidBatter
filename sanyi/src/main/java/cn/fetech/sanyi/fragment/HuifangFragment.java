package cn.fetech.sanyi.fragment;


import android.content.Intent;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;

import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.AddFollowUpActivity;
import cn.fetech.sanyi.adapter.FollowAdapter;
import cn.fetech.sanyi.bean.FollowUp;

import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.data.TestData;

/**
 * A simple {@link Fragment} subclass.
 */
public class HuifangFragment extends BatterLoadingFragment{


    public HuifangFragment() {
        // Required empty public constructor
    }


    @ViewInject(R.id.listview)
    private ListView listView;

    private List<FollowUp> list = new ArrayList<>();

    private FollowAdapter followAdapter;

    @ViewInject(R.id.foolow_up)
    private Button addFollowUp;


    private User user;

    public static ResourcesFollowUp instance(User user) {
        ResourcesFollowUp fragemnt = new ResourcesFollowUp();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);
        fragemnt.setArguments(bundle);
        return fragemnt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //user = (User) getArguments().getSerializable("User");


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_resources_follow_up;
    }

    @Override
    protected void initData() {
        super.initData();
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_next_huifang, null);
        listView.addHeaderView(headView);
        followAdapter = new FollowAdapter(list, getActivity());
        listView.setAdapter(followAdapter);



        addFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFollowUpActivity.class);
                intent.putExtra("title","写回访");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        BatterTaskItem batterTaskItem = new BatterTaskItem(responseListner, new DoWork() {
            @Override
            public boolean doWorkInThread() {
                List<FollowUp> followUps = TestData.createFollowUps();
                list.clear();
                list.addAll(followUps);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();

    }

    @Override
    protected boolean requestSuccess(String result) {
        followAdapter.notifyDataSetChanged();
        return Boolean.valueOf(result);
    }


}
