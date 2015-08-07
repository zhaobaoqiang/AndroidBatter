package cn.fetech.sanyi.fragment;



import android.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;
import com.wudoumi.batter.view.pullableview.State;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.adapter.ZhantingAdapter;
import cn.fetech.sanyi.bean.Zhanting;
import cn.fetech.sanyi.data.TestData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhangtingFragment extends BatterLoadingFragment implements PullToRefreshLayout.OnRefreshListener{


    public ZhangtingFragment() {
        // Required empty public constructor
    }


    @ViewInject(R.id.listview)
    ListView listView;

    @ViewInject(R.id.refresh)
    PullToRefreshLayout refreshLayout;


    int state = 0;

    private List<Zhanting> list = new ArrayList<>();


    private ZhantingAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {

        listView.setDivider(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ZhantingAdapter(list,getActivity());
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);


    }

    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        state = 0;
        doRequest(responseListner);
    }

    @Override
    protected boolean requestSuccess(String result) {
        adapter.notifyDataSetChanged();
        return true;
    }


    @Override
    protected void requestEnd(boolean success) {
        super.requestEnd(success);
        if(state==1){
            refreshLayout.refreshFinish(success? State.SUCCEED:State.FAIL);
        }else if(state==2){
            refreshLayout.loadmoreFinish(success ? State.SUCCEED : State.FAIL);
        }
    }


    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        state = 1;
        doRequest(getResponseListner());
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        state = 2;
        doRequest(getResponseListner());
    }


    private void doRequest(BatterLoadResponseListner responseListner){
        BatterTaskItem batterTaskItem = new BatterTaskItem(responseListner, new DoWork() {
            @Override
            public boolean doWorkInThread() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(state!=2){
                    list.clear();
                }
                list.addAll(TestData.createZhangTing());
                return true;
            }
        });

        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();
    }


}
