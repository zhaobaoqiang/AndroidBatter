package cn.fetech.sanyi.activity;





import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.TxlUserFragment;

public class TxlUserActivity extends BaseActivity {

    private String txlUserZuming;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_txl_user;
    }

    @Override
    protected String getToolBarTitle() {
        return txlUserZuming;
    }

    @Override
    protected void initLocalData() {

        txlUserZuming = getIntent().getStringExtra("txlUserZuming");
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new TxlUserFragment()).commit();
    }
}
