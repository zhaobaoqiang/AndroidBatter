package com.wudoumi.battertest;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;

import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/17 0017.
 */
public class TestPullListview extends BatterActivity{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_pulllistview);
        PullToRefreshLayout p = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        p.setOnRefreshListener(new MyListener());
        listView = (ListView) findViewById(R.id.content_view);
        initListView();

        p.refrush();

    }


    private void initListView()
    {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 30; i++)
        {
            items.add("这里是item " + i);
        }
        MyAdapter adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);

    }


    private void test(){
        //ListView
       // GridView



    }
}
