package cn.fetech.sanyi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.wudoumi.batter.base.BatterActivity;

/**
 * Created by qianjujun on 2015/7/23 0023 09:55.
 * qianjujun@163.com
 */
public abstract class BaseActivity extends BatterActivity{

    protected Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        initLocalData();

        initToolBar();

        initView();

    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getToolBarTitle());
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    protected abstract int getLayoutId();

    protected void initLocalData(){

    }

    protected void initView(){

    }

    protected abstract String getToolBarTitle();





    @Override
    public boolean onKeyUp(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                return true;
        }

        return super.onKeyUp(keycode, e);
    }

}
