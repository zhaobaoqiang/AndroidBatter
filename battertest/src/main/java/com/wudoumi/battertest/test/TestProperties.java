package com.wudoumi.battertest.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.AppUtil;
import com.wudoumi.battertest.R;

public class TestProperties extends BatterActivity {

    @ViewInject(R.id.textview)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_properties);


        tv.setText(AppUtil.getInstance().getAssentStringConfig("config.properties","SimpleLoadView"));
    }






}
