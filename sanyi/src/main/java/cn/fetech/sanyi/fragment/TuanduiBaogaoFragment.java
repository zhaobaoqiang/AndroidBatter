package cn.fetech.sanyi.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.adapter.TeamBaogaoAdapter;
import cn.fetech.sanyi.bean.TxlUser;
import cn.fetech.sanyi.data.TestData;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuanduiBaogaoFragment extends BatterFragment {


    @ViewInject(R.id.listview)
    ExpandableListView expandableListView;


    public TuanduiBaogaoFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tuandui_baogao;
    }


    @Override
    protected void initData() {
        List<TxlUser> txlUsers = TestData.createTxlUsers();
        TxlUser tuandui = new TxlUser();
        tuandui.setName("团队");
        txlUsers.add(0,tuandui);


        TeamBaogaoAdapter teamBaogaoAdapter = new TeamBaogaoAdapter(getActivity(),txlUsers);

        expandableListView.setAdapter(teamBaogaoAdapter);
    }
}
