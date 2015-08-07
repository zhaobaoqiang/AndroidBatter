package cn.fetech.sanyi.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.AddContactsActivity;
import cn.fetech.sanyi.activity.AddFollowUpActivity;
import cn.fetech.sanyi.bean.ResourcesContacts;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.data.TestData;

/**
 *
 */
public class ResourcesContactsFragment extends BatterLoadingFragment {

    @ViewInject(R.id.listview)
    private ListView listview;

    @ViewInject(R.id.add_contacts)
    private Button add_contacts;

    private ContactsAdapter contactsAdapter;

    private List<ResourcesContacts> list = new ArrayList<>();



    private User user;

    public static ResourcesContactsFragment instance(User user) {
        ResourcesContactsFragment fragemnt = new ResourcesContactsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);
        fragemnt.setArguments(bundle);
        return fragemnt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (User) getArguments().getSerializable("User");

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_resources_contacts;
    }

    @Override
    protected void initData() {
        super.initData();
        contactsAdapter = new ContactsAdapter(list,getActivity());
        listview.setAdapter(contactsAdapter);
        if (user.getUserType2() == User.TYPE2_PUBLIC) {
            add_contacts.setVisibility(View.GONE);
        } else {
            add_contacts.setVisibility(View.VISIBLE);
            add_contacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddContactsActivity.class);
                    startActivityForResult(intent,100);
                }
            });


            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    phone(contactsAdapter.getItem(position).getPhone());
                }
            });
        }




        
    }

    private void phone(String phone){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode== Activity.RESULT_OK&&requestCode==100&&data!=null){
            ResourcesContacts resourcesContacts = (ResourcesContacts) data.getSerializableExtra("User");
            list.add(0,resourcesContacts);
            contactsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        BatterTaskItem batterTaskItem = new BatterTaskItem(responseListner, new DoWork() {
            @Override
            public boolean doWorkInThread() {
                List<ResourcesContacts> temp = TestData.createResourcesUsers();
                list.clear();
                list.addAll(temp);
                return true;
            }
        });
        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();
    }

    @Override
    protected boolean requestSuccess(String result) {
        contactsAdapter.notifyDataSetChanged();
        return list.size()>0;
    }


    class ContactsAdapter extends BatterAdapter<ResourcesContacts>{

        public ContactsAdapter(List<ResourcesContacts> list, Context context) {
            super(list, context);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            ResourcesContacts user = getItem(i);
            if(view ==null){
                view = inflater.inflate(R.layout.item_resources_user,null);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.tvName.setText(user.getName());
            viewHolder.tvPhone.setText(user.getPhone());
            return view;
        }

        class ViewHolder{
            TextView tvName,tvPhone,tvTop,tvBottom;
            ImageView ivHead;

            public ViewHolder(View conertView) {
                tvName = (TextView) conertView.findViewById(R.id.name);
                tvPhone = (TextView) conertView.findViewById(R.id.phone);
                conertView.setTag(this);
            }
        }
    }
}
