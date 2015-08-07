package cn.fetech.sanyi.fragment;



import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;
import com.wudoumi.batter.view.pullableview.State;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.AddTaskActivity;
import cn.fetech.sanyi.activity.CreateHetongActivity;
import cn.fetech.sanyi.activity.CreateOrderActivity;
import cn.fetech.sanyi.activity.CustomTuihuanActivity;
import cn.fetech.sanyi.activity.QuhuoActivity;
import cn.fetech.sanyi.activity.ResourcesUserActivity;
import cn.fetech.sanyi.activity.SupplierGaijiaActivity;
import cn.fetech.sanyi.adapter.MessageAdapter;
import cn.fetech.sanyi.bean.Message;
import cn.fetech.sanyi.bean.Task;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.data.TestData;
import cn.fetech.sanyi.util.SimpleProgress;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BatterLoadingFragment  implements PullToRefreshLayout.OnRefreshListener{
    @ViewInject(R.id.listview)
    ListView listView;

    @ViewInject(R.id.refresh)
    PullToRefreshLayout refreshLayout;


    int state = 0;

    private List<Message> list = new ArrayList<>();


    private MessageAdapter adapter;


    private PopupWindow popupWindow;


    private Message currentMessage = null;



    private String[] popList = new String[]{"转任务","删除"};

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {
        adapter = new MessageAdapter(list,getActivity());
        listView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);




        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentMessage = adapter.getItem(position);

                showPop();
                return true;
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
                list.addAll(TestData.getMessages());
                return true;
            }
        });

        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();
    }


    private void initPop() {


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_user_menu, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.item_pop_user, R.id.text, popList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }

                if(currentMessage==null){
                    return;
                }


                if(position==0){
//                    Task task = new Task(currentMessage);
//                    convertTask(task);


                    Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                    intent.putExtra("Content",currentMessage.getContent());
                    startActivity(intent);

                }else if(position==1){
                    list.remove(currentMessage);
                    adapter.notifyDataSetChanged();
                }


            }
        });
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
    }

    private void showPop(){
        if(popupWindow==null){
            initPop();
        }
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }



//    private void convertTask(final Task task){
//        BatterTaskItem batterTaskItem = new BatterTaskItem(new SimpleProgress(getActivity(), "转为任务中..."){
//            @Override
//            public void onSuccess(String result) {
//                super.onSuccess(result);
//
//                if(Boolean.parseBoolean(result)){
//                    sendBroad(task);
//                }
//            }
//        }, new DoWork() {
//            @Override
//            public boolean doWorkInThread() {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return true;
//            }
//        });
//
//
//        BatterTask batterTask = new BatterTask(batterTaskItem);
//        batterTask.execute();
//
//    }
//
//
//    public void sendBroad(Task task){
//        Intent intent = new Intent(Constant.BROADCAST_CONVERT_TASK_SUCCESS);
//        intent.putExtra("Task",task);
//        getActivity().sendBroadcast(intent);
//    }
}
