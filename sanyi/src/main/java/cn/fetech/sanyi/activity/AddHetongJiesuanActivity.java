package cn.fetech.sanyi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wudoumi.batter.utils.ToastUtil;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;


/**
 * 新曾合同结算
 */
public class AddHetongJiesuanActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_hetong_jiesuan;
    }

    @Override
    protected String getToolBarTitle() {
        return "添加结算";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_hetong_jiesuan, menu);
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
            ToastUtil.showToast(this,"保存");
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
