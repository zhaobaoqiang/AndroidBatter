package cn.fetech.sanyi.activity;


import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.fragment.DiuHetongFragment;
import cn.fetech.sanyi.fragment.DiuOrderFragment;
import cn.fetech.sanyi.fragment.TestFragment;
import cn.fetech.sanyi.fragment.YingHetongFragment;
import cn.fetech.sanyi.fragment.YingOrderFragment;

public class AddYingdiuOrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{


    @ViewInject(R.id.rg1)
    RadioGroup rg1;
    @ViewInject(R.id.rg2)
    RadioGroup rg2;


    private int type = Constant.YING_HETONG;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_yingdiu_order;
    }

    @Override
    protected String getToolBarTitle() {
        return "添加赢丢单分析";
    }

    @Override
    protected void initView() {
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        updateType();
        showFragment(type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_yingdiu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.commit) {
            ToastUtil.showToast(this,"正在提交");
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        updateType();

        showFragment(type);


    }


    private void updateType(){
        switch (rg1.getCheckedRadioButtonId()){
            case R.id.rb_yingdan:
                switch (rg2.getCheckedRadioButtonId()){
                    case R.id.rb_hetong:
                        type = Constant.YING_HETONG;
                        break;
                    case R.id.rb_order:
                        type = Constant.YING_ORDER;
                        break;
                }
                break;
            case R.id.rb_diudan:
                switch (rg2.getCheckedRadioButtonId()){
                    case R.id.rb_hetong:
                        type = Constant.DIU_HETONG;
                        break;
                    case R.id.rb_order:
                        type = Constant.DIU_ORDER;
                        break;
                }
                break;

        }
    }


    private Map<Integer,Fragment> map = new HashMap<>();

    private Fragment getFragment(int type){
        if(!map.containsKey(type)){
            Fragment fragment = null;
            switch (type){
                case Constant.YING_HETONG:
                    fragment = new YingHetongFragment();
                    //fragment = TestFragment.instance("赢单 签合同");
                    break;
                case Constant.YING_ORDER:
                    fragment = new YingOrderFragment();
                    //fragment = TestFragment.instance("赢单 签订单");
                    break;
                case Constant.DIU_HETONG:
                    fragment = new DiuHetongFragment();
                    //fragment = TestFragment.instance("丢单 签合同");
                    break;
                case Constant.DIU_ORDER:
                    fragment = new DiuOrderFragment();
                    //fragment = TestFragment.instance("丢单 签订单");
                    break;
            }
            map.put(type,fragment);
        }


        return map.get(type);
    }

    private void showFragment(int type){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,getFragment(type)).commit();
    }
}
