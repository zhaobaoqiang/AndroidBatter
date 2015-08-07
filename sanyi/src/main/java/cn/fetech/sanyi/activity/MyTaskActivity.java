package cn.fetech.sanyi.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.fragment.TaskFragment;
import cn.fetech.sanyi.fragment.WeekFragment;

public class MyTaskActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_task;
    }

    @Override
    protected String getToolBarTitle() {
        return "我的任务";
    }




    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        WeekFragment weekFragment = new WeekFragment();
        TaskFragment taskFragment = new TaskFragment();
        ft.replace(R.id.weekFragment, weekFragment);
        ft.replace(R.id.taskFragment,taskFragment);
        ft.commit();

        weekFragment.setOnDateChangeListner(taskFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_task, menu);
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
