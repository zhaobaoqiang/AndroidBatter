package com.wudoumi.httptest;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.HttpRequetParem;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.net.ResponseListner;
import com.wudoumi.batter.net.VolleyNet;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends BatterActivity {

    @ViewInject(R.id.etUrl)
    EditText etUrl;


    @ViewInject(R.id.content)
    TextView tvContent;

    @ViewInject(R.id.etParem)
    TextView etParem;

    @ViewInject(R.id.radioParent)
    RadioGroup radioParent;

    NetInterface netInterface;


    DownloadManager downloadManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        netInterface = VolleyNet.getInstance(getApplication());

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        setContentView(R.layout.activity_main);


        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        netInterface.relase();

    }

    public void performClick(View view) {
        String url = etUrl.getText().toString();
        if (!url.startsWith("http://")) {
            //ToastUtil.showToast(this,"url格式错误");
            url = "http://" + url;

        }

        if (!url.startsWith("http://")) {
            ToastUtil.showToast(this, "url格式错误");
            return;
        }
        switch (radioParent.getCheckedRadioButtonId()) {
            case R.id.radioGet:
                doGet(url);
                break;
            case R.id.radioPost:
                doPost(url);
                break;
            case R.id.radioDownload:
                doDownLoad(url);
                break;
        }
    }


    public void performClick1(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "TestDownLoad");


        final String str = new File(file, "test.jpg").getAbsolutePath();

        //Log.d("performClick1","-----"+file.listFiles());

        //Log.d("performClick1","*******"+file.getAbsolutePath());

        //Environment.getExternalStorageDirectory().
//        List<String> path = new ArrayList<>();
//        Map<String,String> map = new HashMap<>();
//
//        TestUpload testUpload = new TestUpload();
//
//        testUpload.upload(path,map);


        new Thread() {
            @Override
            public void run() {
//                List<String> path = new ArrayList<>();
//                path.add(str);
//                Map<String,String> map = new HashMap<String, String>();
//                map.put("key","testkey");
//                map.put("value","testvalue");
//                map.put("userId","testuserId");
//                TestUpload testUpload = new TestUpload();
//                testUpload.upload(path,map);

                testUpload();

            }
        }.start();
    }

    private void testUpload() {
        HttpPostUtil u = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "TestDownLoad/test.jpg");
            u = new HttpPostUtil("http://192.168.1.135:8080/mobile/filesModel/uploadFile.json?key=2010&value=2010");
            u.addFileParameter("file", file);
            u.addTextParameter("userId", "testuserId");


            byte[] b = u.send();
            String result = new String(b);
            //System.out.println(result);
            Log.d("BufferedReader", "---------" + result);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("BufferedReader", "++++++++" + e.getMessage());
        }

    }


    private void doGet(String url) {
        RequestParem requestParem = new HttpRequetParem(RequestType.GET, url);
        requestParem.setMapParem(getMapForStr());
        netInterface.doRequest(requestParem, new SimpleListner());

    }


    private void doPost(String url) {
        RequestParem requestParem = new HttpRequetParem(RequestType.POST, url);
        requestParem.setMapParem(getMapForStr());
        netInterface.doRequest(requestParem, new SimpleListner());
    }

    private void doDownLoad(String url) {
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationInExternalPublicDir("TestDownLoad", "");

        myDownloadReference = downloadManager.enqueue(request);

    }

    long myDownloadReference;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (myDownloadReference == reference) {
                try {
                    downloadManager.openDownloadedFile(reference);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private Map<String, String> getMapForStr() {
        String str = etParem.getText().toString();
        Map<String, String> map = new HashMap<>();
        try {
            String[] strs = str.split(";");
            for (String mapstr : strs) {
                String[] mapParem = mapstr.split(":");
                map.put(mapParem[0], mapParem[1]);
            }
        } catch (Exception e) {

        }
        return map;
    }


    class SimpleListner extends ResponseListner {
        ProgressDialog progressDialog;

        @Override
        public void onStart() {
            super.onStart();
            progressDialog = ProgressDialog.show(MainActivity.this, "", "正在请求...", true, true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancel();
                }
            });
        }

        @Override
        public void onSuccess(String result) {
            super.onSuccess(result);
            tvContent.setText(result);
        }

        @Override
        public void onError(BatterExcetion error) {
            tvContent.setText(error.getMessage());
        }

        @Override
        public void onEnd() {
            super.onEnd();
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


}
