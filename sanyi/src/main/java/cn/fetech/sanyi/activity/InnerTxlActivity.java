package cn.fetech.sanyi.activity;



import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.MainTxlFragment;

public class InnerTxlActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_inner_txl;
    }

    @Override
    protected String getToolBarTitle() {
        return "内部通讯录";
    }

    @Override
    protected void initView() {

        getSupportFragmentManager().beginTransaction().replace(R.id.content,new MainTxlFragment()).commit();
    }
}
