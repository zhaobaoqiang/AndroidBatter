package com.wudoumi.battertest.test;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wudoumi.battertest.R;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);


        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction f = fragmentManager.beginTransaction();



        f.replace(R.id.post,VolleyActivityFragment.newInstance(2));
        f.replace(R.id.get,VolleyActivityFragment.newInstance(3));
        f.replace(R.id.soap,VolleyActivityFragment.newInstance(1));


        f.commit();

    }



}
