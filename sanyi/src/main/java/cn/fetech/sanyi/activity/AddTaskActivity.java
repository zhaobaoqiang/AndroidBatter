package cn.fetech.sanyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.StringUtil;
import com.wudoumi.batter.utils.ToastUtil;

import java.text.SimpleDateFormat;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Task;
import cn.fetech.sanyi.bean.TxlUser;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.widget.DateTimePop;
import cn.fetech.sanyi.widget.OnSelectComplete;
import cn.fetech.sanyi.widget.SingleWheelPop;

public class AddTaskActivity extends BaseActivity {

    @ViewInject(R.id.startTime)
    private TextView tvStartTime;

    @ViewInject(R.id.endTime)
    private TextView tvEndTime;

    @ViewInject(R.id.youxianji)
    private TextView tvYouxianji;

    @ViewInject(R.id.fuzeren)
    private TextView tvFuzenren;

    @ViewInject(R.id.content)
    private EditText tvContent;



    private String content;
    private TxlUser fuzeren;
    private boolean zhyao;
    private boolean jinji;

    private long startTime;
    private long endTime;


    private DateTimePop startPop;
    private DateTimePop endPop;

    private SingleWheelPop zhyaoPop;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_task;
    }

    @Override
    protected String getToolBarTitle() {
        return "新建任务";
    }


    @Override
    protected void initLocalData() {
        content = getIntent().getStringExtra("Content");
    }

    @Override
    protected void initView() {
        if(!StringUtil.isEmpty(content)){
            tvContent.setText(content);
        }
    }

    public void perfromClick(View view){
        switch (view.getId()){
            case R.id.startTime:
                if(startPop==null){
                    startPop = new DateTimePop(this,new SimpleOnSelectComplete(tvStartTime){
                        @Override
                        public void onChoose(Long result) {
                            super.onChoose(result);
                            startTime = result;
                        }
                    });
                }
                startPop.show();
                break;
            case R.id.endTime:
                if(endPop==null){
                    endPop = new DateTimePop(this,new SimpleOnSelectComplete(tvEndTime){
                        @Override
                        public void onChoose(Long result) {
                            super.onChoose(result);
                            endTime = result;
                        }
                    });
                }
                endPop.show();
                break;
            case R.id.youxianji:
                if(zhyaoPop==null){
                    zhyaoPop = new SingleWheelPop(this, new OnSelectComplete<String>() {
                        @Override
                        public void onChoose(String result) {
                            tvYouxianji.setText(result);
                            if("重要".equals(result)){
                                zhyao = true;
                                jinji = false;
                            }else if("紧急".equals(result)){
                                zhyao = false;
                                jinji = true;
                            }else if("重要且紧急".equals(result)){
                                zhyao = true;
                                jinji = true;
                            }else{
                                zhyao = false;
                                jinji = false;
                            }
                        }

                        @Override
                        public View getRootView() {
                            return getWindow().getDecorView();
                        }
                    }, Task.importances);
                }
                zhyaoPop.show();
                break;
            case R.id.fuzeren:
                selectUser();

                break;
        }
    }


    private void selectUser(){
        Intent intent = new Intent(this,InnerTxlActivity.class);
        intent.putExtra("order", Constant.SECLCT_USER_FUZEREN_ORDER);
        startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent!=null&&intent.getIntExtra("order",0)==Constant.SECLCT_USER_FUZEREN_ORDER){
            fuzeren = (TxlUser) intent.getSerializableExtra("User");

            tvFuzenren.setText(fuzeren.getName());
        }
    }


    class SimpleOnSelectComplete implements OnSelectComplete<Long> {
        private final TextView tv;

        public SimpleOnSelectComplete(TextView tv) {
            this.tv = tv;
        }

        @Override
        public void onChoose(Long result) {
            setDate(tv,result);
        }

        @Override
        public View getRootView() {
            return getWindow().getDecorView();
        }
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private void setDate(TextView tv,long time){
        tv.setText(sdf.format(time));
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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
            Task task = check();
            if(task!=null){
                saveTask(task);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private Task check(){
        Task task = new Task();
        content = tvContent.getText().toString();
        if(StringUtil.isEmpty(content)){
            ToastUtil.showToast(this,"请输入内容");
            return null;
        }
        task.setTaskContent(content);

        if(startTime==0){
            ToastUtil.showToast(this,"请选择开始时间");
            return null;
        }
        task.setStartTime(startTime);

        if(endTime==0||endTime<startTime){
            ToastUtil.showToast(this,"结束时间必须大于开始时间");
            return null;
        }
        task.setEndTime(endTime);

        if(fuzeren==null){
            ToastUtil.showToast(this,"请选择负责人");
            return null;
        }
        task.setUserId(fuzeren.getId());

        task.setImportance(zhyao);
        task.setPriority(jinji);
        return task;
    }


    private void saveTask(Task task){

        ToastUtil.showToast(this,"保存");
    }
}
