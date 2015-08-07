package cn.fetech.sanyi.activity;


import android.content.Intent;
import android.view.View;

import com.wudoumi.batter.utils.ToastUtil;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;

public class MimeActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_mime;
    }

    @Override
    protected String getToolBarTitle() {
        return "我的";
    }



    public void performClick(View view){
        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.qingjiadan:
                intent.setClass(this,QingjiadanActivity.class);
                startActivity(intent);
                break;
            case R.id.renwuzhxin:
                intent.setClass(this,MyTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.xiaoxizhxin:
                intent.setClass(this,MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.gerenziliao:
                intent.setClass(this,UserDetailActivity.class);
                startActivity(intent);
                break;
            default:
                ToastUtil.showToast(this,"暂未实现");
                break;
        }
    }
}
