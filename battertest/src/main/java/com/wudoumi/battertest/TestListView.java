package com.wudoumi.battertest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public class TestListView extends BatterActivity {
    Handler handler = new Handler();
    List<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_listview);
        lv = (ListView) findViewById(R.id.list);



        String[] items = new String[]{"aaaaa", "bbbbb", "cccccc", "dddddddd"};



        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, list);

        lv.setAdapter(adapter);

        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        emptyView.setText("This appears when the list is empty");
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)lv.getParent()).addView(emptyView);
        lv.setEmptyView(emptyView);


    }


    public void btnClick(View view){
        switch (view.getId()){
            case R.id.clear:
                list.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.add:
                list.add("测试");
                adapter.notifyDataSetChanged();
                break;
        }
    }


}
