package cn.fetech.sanyi.activity;



import android.content.Intent;
import android.view.View;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.QingjiadanFragment;

public class QingjiadanActivity extends BaseActivity {

    private QingjiadanFragment qingjiadanFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qingjiadan;
    }

    @Override
    protected String getToolBarTitle() {
        return "请假单";
    }



    @Override
    protected void initView() {
        qingjiadanFragment = new QingjiadanFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.content,qingjiadanFragment).commit();
    }


}
