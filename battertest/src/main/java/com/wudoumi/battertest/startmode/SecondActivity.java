package com.wudoumi.battertest.startmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wudoumi.battertest.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        TextView tv = (TextView) findViewById(R.id.text);

        tv.setText(this.toString()+"\r\n"+getTaskId());
    }


    public void performClick(View view){
        startActivity(new Intent(this,FirstActivity.class));
    }


}
