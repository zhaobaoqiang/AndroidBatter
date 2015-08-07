package cn.fetech.sanyi;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.wq.photo.MediaChoseActivity;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ScreenManager;

import java.lang.reflect.Method;

import cn.fetech.sanyi.activity.MessageActivity;


public class MainActivity extends BaseActivity {

    @ViewInject(R.id.drawer)
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public String getToolBarTitle() {
        return "首页";
    }

    @Override
    protected void initView() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.slide_opne, R.string.slide_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
         if(id==R.id.action_message){
             Intent intent=new Intent(this, MessageActivity.class);

             startActivity(intent);
             return true;
         }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public  static  final int REQUEST_IMAGE=1000;

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (menu!=null&&menu.getClass().getSimpleName().equals("MenuBuilder")) {
//            try {
//                Method m = menu.getClass().getDeclaredMethod(
//                        "setOptionalIconsVisible", Boolean.TYPE);
//                m.setAccessible(true);
//                m.invoke(menu, true);
//
//                Log.d("onMenuOpened","success");
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//                Log.d("onMenuOpened","error:"+e.getMessage());
//            } catch (Exception e) {
//                Log.d("onMenuOpened","error:"+e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }


    @Override
    public boolean onKeyUp(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                mToolbar.showOverflowMenu();
                return true;
        }

        return super.onKeyUp(keycode, e);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();



    }



    public void exit(){
        SanyiApp.getInstance().getNetInterface().relase();
        ScreenManager.getScreenManager().popAllActivity();
    }
}
