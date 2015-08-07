package cn.fetech.sanyi.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;
import com.wudoumi.batter.view.pullableview.State;
import com.wudoumi.batter.volley.toolbox.RequestParem;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.SanyiApp;
import cn.fetech.sanyi.activity.TaskDetailActivity;
import cn.fetech.sanyi.adapter.MessageAdapter;
import cn.fetech.sanyi.adapter.TaskAdapter;
import cn.fetech.sanyi.bean.JsonVo;
import cn.fetech.sanyi.bean.Message;
import cn.fetech.sanyi.bean.Task;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.data.NetDataParem;
import cn.fetech.sanyi.data.TestData;
import cn.fetech.sanyi.widget.ListStringPop;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends BatterLoadingFragment implements PullToRefreshLayout.OnRefreshListener, WeekFragment.OnDateChangeListner {
    @ViewInject(R.id.listview)
    ListView listView;

    @ViewInject(R.id.refresh)
    PullToRefreshLayout refreshLayout;


    int state = 0;

    private List<Task> list = new ArrayList<>();


    private TaskAdapter adapter;


    private NetInterface netInterface;


    private RequestParem currentParem;

    private int currentPage = Constant.START_PAGE;


    private ListStringPop listStringPop;

    private List<String> listPop;

    private AdapterView.OnItemClickListener onItemClickListener;


    private Task currentTask = null;

    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netInterface = SanyiApp.getInstance().getNetInterface();

        currentParem = NetDataParem.getTaskParem(1,Constant.TASK_ALL);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {
        adapter = new TaskAdapter(list, getActivity());
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentTask = adapter.getItem(position);
                if(listStringPop==null){
                    initPop();
                }
                listStringPop.show();
                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
                intent.putExtra("Task",task);
                startActivity(intent);
            }
        });
    }




    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        state = 0;
        doRequest(responseListner);
    }

    @Override
    protected boolean requestSuccess(String result) {
        boolean success = false;
        Gson gson = new Gson();
        try {
            JsonVo<List<Task>> jsonVo = gson.fromJson(result, new TypeToken<JsonVo<List<Task>>>() {
            }.getType());
            if (jsonVo != null && jsonVo.isSuccess()) {
                List<Task> tasks = jsonVo.getResults();
                if (tasks != null && tasks.size() > 0) {
                    if (state != 2) {
                        list.clear();
                    }
                    list.addAll(TestData.createTestTasks());
                    adapter.notifyDataSetChanged();
                    success = true;
                }else{
                    if(currentPage>Constant.START_PAGE){
                        currentPage--;
                    }
                }
            }
        } catch (Exception e) {
            if(currentPage>Constant.START_PAGE){
                currentPage--;
            }
            ToastUtil.showToast(getActivity(), "数据解析错误");
        }

        return success;
    }


    @Override
    protected void requestEnd(boolean success) {
        super.requestEnd(success);
        if (state == 1) {//刷新结束
            refreshLayout.refreshFinish(success ? State.SUCCEED : State.FAIL);
        } else if (state == 2) {//加载结束
            refreshLayout.loadmoreFinish(success ? State.SUCCEED : State.FAIL);
        }
    }


    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        state = 1;
        currentPage = Constant.START_PAGE;
        currentParem.put("page",currentPage+"");
        doRequest(getResponseListner());
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        state = 2;
        currentPage++;
        currentParem.put("page",currentPage+"");
        doRequest(getResponseListner());
    }


    private void doRequest(BatterLoadResponseListner responseListner) {


        netInterface.doRequest(currentParem, responseListner);

    }

    @Override
    public void onDateChange(long newDate) {
        handlerEmptyNoRequest();
        state = 0;
        currentPage = Constant.START_PAGE;
        currentParem = NetDataParem.getTaskParem(1,newDate);
        currentParem.put("page",currentPage+"");
        doRequest(getResponseListner());
    }



    private void initPop(){
        listPop = new ArrayList<>();
        listPop.add("完成");
        listPop.add("删除");

        onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listStringPop.dismiss();
                switch (position){
                    case 0:
                        currentTask.setState("已完成");
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        list.remove(currentTask);
                        adapter.notifyDataSetChanged();
                        break;

                }
            }
        };

        listStringPop = new ListStringPop(getActivity(),listPop,onItemClickListener);
    }
}
