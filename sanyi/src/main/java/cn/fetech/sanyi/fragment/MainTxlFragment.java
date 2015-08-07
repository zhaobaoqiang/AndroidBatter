package cn.fetech.sanyi.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.TxlUserActivity;
import cn.fetech.sanyi.adapter.TxlAdapter;
import cn.fetech.sanyi.bean.TxlParent;
import cn.fetech.sanyi.data.TestData;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTxlFragment extends BatterFragment {

    @ViewInject(R.id.expandableListView)
    private ExpandableListView expandableListView;

    public MainTxlFragment() {
        // Required empty public constructor
    }

    List<TxlParent> parent = new ArrayList<>();
    Map<Integer,List<TxlParent>> child = new HashMap<>();
    @Override
    protected int getLayoutId() {

        return R.layout.fragment_main_txl;
    }


    @Override
    protected void initData() {
        super.initData();

        List<TxlParent> list = TestData.getTxlParents();



        convert(list,parent,child);

        final TxlAdapter txlAdapter = new TxlAdapter(parent,child,getActivity());

        expandableListView.setAdapter(txlAdapter);


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String title = ((TxlParent)txlAdapter.getChild(groupPosition,childPosition)).getName();

                Intent intent = new Intent(getActivity(), TxlUserActivity.class);
                intent.putExtra("order",getActivity().getIntent().getIntExtra("order",0));
                intent.putExtra("txlUserZuming",title);
                startActivity(intent);
                return true;
            }
        });
    }


    private void convert(List<TxlParent> list,List<TxlParent> parent,Map<Integer,List<TxlParent>> child){
        List<TxlParent> childTemps = null;
        for(TxlParent txlParent:list){
            if(txlParent.getParentId()==0){
                parent.add(txlParent);
                childTemps = new ArrayList<>();
                for(TxlParent childTemp:list){
                    if(childTemp.getParentId()==txlParent.getId()){
                        childTemps.add(childTemp);
                    }
                }
                child.put(txlParent.getId(),childTemps);
            }
        }
    }


}
