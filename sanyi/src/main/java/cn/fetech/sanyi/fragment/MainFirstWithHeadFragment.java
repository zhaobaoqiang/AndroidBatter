package cn.fetech.sanyi.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.listview.stikyyheader.StikkyHeaderBuilder;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.SanyiApp;
import cn.fetech.sanyi.activity.MyTaskActivity;
import cn.fetech.sanyi.activity.TaskDetailActivity;
import cn.fetech.sanyi.adapter.TaskAdapter;
import cn.fetech.sanyi.bean.JsonVo;
import cn.fetech.sanyi.bean.Task;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.data.NetDataParem;
import cn.fetech.sanyi.data.TestData;
import cn.fetech.sanyi.util.SimpleProgress;
import cn.fetech.sanyi.widget.ListStringPop;

/**
 * Created by qianjujun on 2015/6/25 0025 17:43.
 * qianjujun@163.com
 */
public class MainFirstWithHeadFragment extends BatterFragment {

    @ViewInject(R.id.listview)
    private ListView mListView;

    @ViewInject(R.id.header)
    private FrameLayout head;

    @ViewInject(R.id.radioGroup)
    private RadioGroup mRadioGroup;

    @ViewInject(R.id.more)
    private TextView more;

    private List<Task> tasks = new ArrayList<>();

    private Map<Integer, List<Task>> map = new HashMap<>();

    private TaskAdapter taskAdapter;

    private NetInterface netInterface;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_first_withhead, null);
    }


    @Override
    protected void initData() {
        super.initData();

        getChildFragmentManager().beginTransaction().replace(R.id.main_first_head, new MainFirstHeadFragment()).commit();

        StikkyHeaderBuilder.stickTo(mListView)
                .setHeader(head)
                .minHeightHeaderDim(R.dimen.q100)
                        //.animator(animator)
                .build();


        taskAdapter = new TaskAdapter(tasks, getActivity());
        mListView.setAdapter(taskAdapter);


        netInterface = SanyiApp.getInstance().getNetInterface();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(!map.containsKey(i)){
                    doNetWork(i);
                }else{
                    notifcationAdapter(map.get(i));
                }

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = taskAdapter.getItem(i-1);
                Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
                intent.putExtra("Task",task);
                startActivity(intent);
            }
        });


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyTaskActivity.class));
            }
        });



        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentTask = taskAdapter.getItem(position-1);//listview 在skitty框架中加入头部
                if(listStringPop==null){
                    initPop();
                }
                listStringPop.show();
                return true;
            }
        });


        doNetWork(R.id.today_task);





    }


    private void doNetWork(final int checkId){
        int type = checkId==R.id.today_task?Constant.TASK_DAY:Constant.TASK_WEEK;
        netInterface.doRequest(NetDataParem.getTaskParem(1,type),new SimpleProgress(getActivity(),"正在获取任务列表..."){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                Gson gson = new Gson();
                try {
                    JsonVo<List<Task>> jsonVo = gson.fromJson(result,new TypeToken<JsonVo<List<Task>>>(){}.getType());
                    List<Task> tasks = null;
                    if(jsonVo!=null&&jsonVo.isSuccess()){
                        tasks = jsonVo.getResults();
                        if(tasks!=null&&tasks.size()>0){
                            map.put(checkId,tasks);
                        }
                    }
                    notifcationAdapter(tasks);
                }catch (Exception e){
                    ToastUtil.showToast(getActivity(),"数据解析错误");

                    notifcationAdapter(null);
                }

            }

            @Override
            public void onError(BatterExcetion error) {
                super.onError(error);
                notifcationAdapter(null);
            }
        });
    }





    private void notifcationAdapter(List<Task> temp) {
        tasks.clear();


        if(temp!=null){
            tasks.addAll(temp);
        }


        taskAdapter.notifyDataSetChanged();
    }



//    class ConvertTask extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Task task = (Task) intent.getSerializableExtra("Task");
//            if(!map.containsKey(R.id.today_task)){
//                map.put(R.id.today_task,new ArrayList<Task>());
//            }
//            map.get(R.id.today_task).add(0,task);
//
//            if(mRadioGroup.getCheckedRadioButtonId()==R.id.today_task){
//                notifcationAdapter(map.get(R.id.today_task));
//            }
//        }
//    }
//
//    private ConvertTask convertTask;
//
//    @Override
//    protected void onCreateViewEnd() {
//        super.onCreateViewEnd();
//        convertTask = new ConvertTask();
//        IntentFilter intentFilter = new IntentFilter(Constant.BROADCAST_CONVERT_TASK_SUCCESS);
//        getActivity().registerReceiver(convertTask,intentFilter);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//        getActivity().unregisterReceiver(convertTask);
//    }




    private ListStringPop listStringPop;

    private List<String> listPop;

    private AdapterView.OnItemClickListener onItemClickListener;


    private Task currentTask = null;


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
                        taskAdapter.notifyDataSetChanged();
                        break;
                    case 1:

                        map.get(mRadioGroup.getCheckedRadioButtonId()).remove(currentTask);

                        notifcationAdapter(map.get(mRadioGroup.getCheckedRadioButtonId()));
                        break;

                }
            }
        };

        listStringPop = new ListStringPop(getActivity(),listPop,onItemClickListener);
    }
}
