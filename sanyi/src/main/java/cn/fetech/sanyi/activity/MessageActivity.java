package cn.fetech.sanyi.activity;



import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.MessageFragment;

public class MessageActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected String getToolBarTitle() {
        return "信息";
    }

    @Override
    protected void initView() {

        getSupportFragmentManager().beginTransaction().replace(R.id.content,new MessageFragment()).commit();
    }
}
