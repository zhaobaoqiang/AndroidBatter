package com.wudoumi.battertest.testemptyview;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.ResponseListner;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;
import com.wudoumi.batter.view.pullableview.State;
import com.wudoumi.battertest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class Main4ActivityFragment extends BatterLoadingFragment implements PullToRefreshLayout.OnRefreshListener,View.OnClickListener{
    @ViewInject(R.id.content_view)
    ListView listview;

    @ViewInject(R.id.refresh_view)
    PullToRefreshLayout refreshLayout;

    @ViewInject(R.id.clear)
    Button btnClear;
    @ViewInject(R.id.carsh)
    Button btnCrash;

    private static int index = 1;

    private List<String> list = new ArrayList<>();

    private List<String> temp = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    private Thread mThread;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main4;
    }


    @Override
    protected void initData() {
        super.initData();
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,list);

        listview.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);

        btnClear.setOnClickListener(this);

        btnCrash.setOnClickListener(this);

    }

    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        createData(responseListner);
    }

    @Override
    protected boolean requestSuccess(String result) {

        boolean success = Boolean.parseBoolean(result);
        if(!success){
            temp.clear();
            return false;
        }
        if(state==1){
            list.addAll(0,temp);
        }else{
            list.addAll(temp);
        }
        temp.clear();
        adapter.notifyDataSetChanged();
        return true;
    }

    private void createData(BatterLoadResponseListner responseListner){

        mThread = new Thread(new WorkRunnable(responseListner));

        mThread.start();
    }

    int state = 0;

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        state = 1;
        createData(getResponseListner());
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        state = 2;
        createData(getResponseListner());
    }

    @Override
    protected void requestEnd(boolean success) {
        super.requestEnd(success);
        switch (state){
            case 1:
                refreshLayout.refreshFinish(success?State.SUCCEED:State.FAIL);
                break;
            case 2:
                if(success){
                    refreshLayout.loadmoreFinish(State.SUCCEED);
                    listview.setSelection(list.size()-10);
                }else{
                    refreshLayout.loadmoreFinish(State.FAIL);
                }

                break;
        }

        state = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear:
                list.clear();
                adapter.notifyDataSetChanged();
                handlerEmptyNoRequest();
                break;
            case R.id.carsh:
                if(mThread.isAlive()){
                    mThread.interrupt();
                }
                break;
        }
    }

    class WorkRunnable implements Runnable{
        Random random = new Random();
        private ResponseListner responseListner;

        public WorkRunnable(ResponseListner responseListner) {
            this.responseListner = responseListner;
        }

        @Override
        public void run() {
            try {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onStart();
                    }
                });
                for(int i = 0;i<10;i++){
                    Thread.sleep(100);
                    temp.add("测试数据" + index++);
                }



                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onSuccess(random.nextBoolean()+"");
                    }
                });

            } catch (final InterruptedException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onError(new BatterExcetion(e));
                    }
                });

            }finally {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onEnd();
                    }
                });

            }
        }
    }
}
