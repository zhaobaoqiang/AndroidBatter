package cn.fetech.sanyi.activity;

import android.view.Menu;
import android.view.MenuItem;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;

public class SettingActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected String getToolBarTitle() {
        return "设置";
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
