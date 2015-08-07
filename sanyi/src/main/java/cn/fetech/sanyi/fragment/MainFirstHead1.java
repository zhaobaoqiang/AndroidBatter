package cn.fetech.sanyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.bean.UserSerach;
import cn.fetech.sanyi.data.Constant;

/**
 * Created by qianjujun on 2015/6/24 0024 16:20.
 * qianjujun@163.com
 */
public class MainFirstHead1 extends BatterFragment implements View.OnClickListener {

    @ViewInject(R.id.icon1)
    private ImageButton icon1;

    @ViewInject(R.id.icon2)
    private ImageButton icon2;

    @ViewInject(R.id.icon3)
    private ImageButton icon3;

    @ViewInject(R.id.icon4)
    private ImageButton icon4;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_first_head1, null);
    }


    @Override
    protected void initData() {
        super.initData();
        icon1.setOnClickListener(this);
        icon2.setOnClickListener(this);
        icon3.setOnClickListener(this);
        icon4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        UserSerach userSerach = null;
        switch (view.getId()) {
            case R.id.icon1:
                userSerach = new UserSerach(User.TYPE_USER_POTENTIAL);
                break;
            case R.id.icon2:
                userSerach = new UserSerach(User.TYPE_USER_INTENTION);
                break;
            case R.id.icon3:
                userSerach = new UserSerach(User.TYPE_USER_QUASI);
                break;
            case R.id.icon4:
                userSerach = new UserSerach(User.TYPE_USER_FORMAL);
                break;
        }

        Intent intent = new Intent(Constant.BROADCAST_SEARCH);
        intent.putExtra("userSerach",userSerach);
        getActivity().sendBroadcast(intent);
    }
}
