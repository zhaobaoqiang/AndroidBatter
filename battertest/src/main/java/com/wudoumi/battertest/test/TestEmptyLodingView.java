package com.wudoumi.battertest.test;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.ThreadLocalSelectArg;
import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.ResponseListner;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.loadview.EmptyLoadView;
import com.wudoumi.batter.view.loadview.Iretry;
import com.wudoumi.battertest.R;

import java.util.ArrayList;
import java.util.List;

public class TestEmptyLodingView extends BatterActivity {
    private static int index = 1;

    @ViewInject(R.id.listView2)
    ListView listview;

    private List<String> list = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    private Thread mThread;


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_empty_loding_view);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,list);

        listview.setAdapter(adapter);

        EmptyLoadView emptyView = new EmptyLoadView(this);


        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        emptyView.setIretry(new Iretry() {
            @Override
            public void retryQuest() {
                createData();
            }
        });
        ((ViewGroup)listview.getParent()).addView(emptyView);
        listview.setEmptyView(emptyView);

        brl = new BatterLoadResponseListner(emptyView){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                adapter.notifyDataSetChanged();
            }
        };

        createData();
    }

    private BatterLoadResponseListner brl;

    private void createData(){

        mThread = new Thread(new WorkRunnable(brl));

        mThread.start();
    }


    class WorkRunnable implements Runnable{
        private ResponseListner responseListner;

        public WorkRunnable(ResponseListner responseListner) {
            this.responseListner = responseListner;
        }

        @Override
        public void run() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onStart();
                    }
                });

                for(int i = 0;i<10;i++){
                    Thread.sleep(300);
                    list.add("测试数据"+index++);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onSuccess("");
                    }
                });

            } catch (final InterruptedException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onError(new BatterExcetion(e));
                    }
                });

            }finally {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseListner.onEnd();
                    }
                });

            }
        }
    }


    public void btnClick(View view){
        switch (view.getId()){
            case R.id.clear:
                list.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.carsh:
                if(mThread.isAlive()){
                    mThread.interrupt();
                }
                break;
        }
    }


}
