package cn.fetech.sanyi.activity;


import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.HuanhuoFragment;
import cn.fetech.sanyi.fragment.TuihuoFragment;

public class CustomTuihuanActivity extends BaseActivity {

    @ViewInject(R.id.radioGroup)
    private RadioGroup radioGroup;

    private Map<Integer,Fragment> map = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_tuihuan;
    }

    @Override
    protected String getToolBarTitle() {
        return "退换货";
    }


    @Override
    protected void initView() {
        showFragment(R.id.tuihuo);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showFragment(checkedId);
            }
        });
    }


    private void showFragment(int id){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,getFragment(id)).commit();
    }

    private Fragment getFragment(int id){
        if(!map.containsKey(id)){
            Fragment fragment = null;
            switch (id){
                case R.id.tuihuo:
                    fragment = new TuihuoFragment();
                    break;
                default:
                    fragment = new HuanhuoFragment();
                    break;
            }
            map.put(id,fragment);
        }
        return map.get(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_tuihuan, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
