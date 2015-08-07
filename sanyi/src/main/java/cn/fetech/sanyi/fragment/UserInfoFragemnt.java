package cn.fetech.sanyi.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.imageview.RoundImageView;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.UserDetailActivity;
import cn.fetech.sanyi.bean.User;

/**
 * Created by qianjujun on 2015/7/6 0006 14:03.
 * qianjujun@163.com
 */
public class UserInfoFragemnt extends BatterFragment{

    private User user;

    @ViewInject(R.id.headView)
    private RoundImageView roundImageView;

    @ViewInject(R.id.username)
    private TextView tvUsername;

    @ViewInject(R.id.phone)
    private TextView tvPhone;

    @ViewInject(R.id.userinfo)
    private Button info;

    public static UserInfoFragemnt instance(User user){
        UserInfoFragemnt fragemnt = new UserInfoFragemnt();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User",user);
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

        return R.layout.fragment_user_info;
    }

    @Override
    protected void initData() {
        super.initData();

        tvUsername.setText(user.getName());
        tvPhone.setText(user.getPhone());

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDetailInfo();
            }
        });


        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone();
            }
        });
    }

    private void toDetailInfo(){
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        startActivity(intent);
    }


    private void phone(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvPhone.getText()));
        startActivity(intent);
    }

}
