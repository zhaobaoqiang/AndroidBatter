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
import cn.fetech.sanyi.fragment.HuanhuoFragment;
import cn.fetech.sanyi.fragment.ShouhouGaijiaFragment;
import cn.fetech.sanyi.fragment.ShouqianGaijiaFragment;
import cn.fetech.sanyi.fragment.TuihuoFragment;

public class SupplierGaijiaActivity extends BaseActivity {

    @ViewInject(R.id.radioGroup)
    private RadioGroup radioGroup;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_supplier_gaijia;
    }

    @Override
    protected String getToolBarTitle() {
        return "改价申请";
    }

    @Override
    protected void initView() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_supplier_gaijia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            ToastUtil.showToast(this, "正在保存");
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    //    private Map<Integer,Fragment> map = new HashMap<>();
//    private void showFragment(int id){
//        getSupportFragmentManager().beginTransaction().replace(R.id.content,getFragment(id)).commit();
//    }
//
//    private Fragment getFragment(int id){
//        if(!map.containsKey(id)){
//            Fragment fragment = null;
//            switch (id){
//                case R.id.shouqian:
//                    fragment = new ShouqianGaijiaFragment();
//                    break;
//                default:
//                    fragment = new ShouhouGaijiaFragment();
//                    break;
//            }
//            map.put(id,fragment);
//        }
//        return map.get(id);
//    }
}
