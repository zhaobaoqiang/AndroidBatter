package com.wudoumi.battertest.testemptyview;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.listview.listviewfilter.ConvertData;
import com.wudoumi.batter.view.listview.listviewfilter.IAutoConvertLetter;
import com.wudoumi.batter.view.listview.listviewfilter.ILetter;
import com.wudoumi.batter.view.listview.listviewfilter.IndexBarView;
import com.wudoumi.batter.view.listview.listviewfilter.ListHashMap;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedAdapter;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedHeaderListView;
import com.wudoumi.battertest.PinnedHeaderAdapter;
import com.wudoumi.battertest.R;
import com.wudoumi.battertest.bean.TestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class TestSortList extends BatterActivity {
    @ViewInject(R.id.list_view)
    PinnedHeaderListView mListView;

    List<TestData> items;


    ListHashMap<String,Integer> map = new ListHashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sort_list);



        items = ConvertData.convert(TestData.getTestData(),map,TestData.class);

        mListView.setAdapter(new SortAdapter(items,this,map));

    }


}
