package cn.fetech.sanyi.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.AddFollowUpActivity;
import cn.fetech.sanyi.activity.AddTaskActivity;
import cn.fetech.sanyi.activity.UserDetailActivity;
import cn.fetech.sanyi.bean.TxlUser;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.data.TestData;

/**
 * A simple {@link Fragment} subclass.
 */
public class TxlUserFragment extends BatterLoadingFragment {

    @ViewInject(R.id.listview)
    private ListView listView;


    private List<TxlUser> list = new ArrayList<>();

    private UserAdapter adapter;

    private int order;

    public TxlUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        order = getActivity().getIntent().getIntExtra("order",0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_txl_user;
    }

    @Override
    protected void initData() {
        adapter = new UserAdapter(list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (order){
                    case Constant.SECLCT_USER_USERDETAIL_ORDER:
                        turnOther(UserDetailActivity.class,adapter.getItem(position).getName());
                        break;
                    case Constant.SECLCT_USER_FOLLOW_ORDER:
                        turnOther(AddFollowUpActivity.class,adapter.getItem(position));
                        break;

                    case Constant.SECLCT_USER_FUZEREN_ORDER:
                        turnOther(AddTaskActivity.class,adapter.getItem(position));
                        break;
                }


            }
        });
    }


    private void turnOther(Class<? extends Activity> clazz,String name){
        Intent intent = new Intent(getActivity(),clazz);
        intent.putExtra("order",order);
        intent.putExtra("userName",name);
        startActivity(intent);
    }

    private void turnOther(Class<? extends Activity> clazz,TxlUser user){
        Intent intent = new Intent(getActivity(),clazz);
        intent.putExtra("order",order);
        intent.putExtra("User",user);
        startActivity(intent);
    }

    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        BatterTaskItem batterTaskItem = new BatterTaskItem(responseListner, new DoWork() {
            @Override
            public boolean doWorkInThread() {
                list.clear();
                list.addAll(TestData.createTxlUsers());
                return true;
            }
        });

        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();
    }

    @Override
    protected boolean requestSuccess(String result) {
        adapter.notifyDataSetChanged();
        return Boolean.parseBoolean(result);
    }


    class UserAdapter extends BatterAdapter<TxlUser> {

        public UserAdapter(List<TxlUser> list) {
            super(list, getActivity());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TxlUser txlUser = getItem(position);
            Holder holder = null;
            if(convertView==null){
                convertView = inflater.inflate(R.layout.item_txluser,null);
                holder = new Holder(convertView);
            }else{
                holder = (Holder) convertView.getTag();
            }

            holder.tvName.setText(txlUser.getName());
            holder.tvRoleName.setText(txlUser.getRoleName());
            holder.tvPhone.setText(txlUser.getPhone());
            return convertView;
        }

        class Holder{
            TextView tvName,tvRoleName,tvPhone;

            public Holder(View conertView) {
                tvName = (TextView) conertView.findViewById(R.id.name);
                tvRoleName = (TextView) conertView.findViewById(R.id.roleName);
                tvPhone = (TextView) conertView.findViewById(R.id.phone);
                conertView.setTag(this);
            }
        }
    }


}
