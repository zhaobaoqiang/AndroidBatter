package cn.fetech.sanyi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.DateUtil;

import java.text.SimpleDateFormat;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Task;

public class TaskDetailActivity extends BaseActivity {

    @ViewInject(R.id.startTime)
    private TextView tvStartTime;

    @ViewInject(R.id.endTime)
    private TextView tvEndTime;

    @ViewInject(R.id.youxianji)
    private TextView tvYouxianji;

    @ViewInject(R.id.fuzeren)
    private TextView tvFuzenren;

    @ViewInject(R.id.content)
    private TextView tvContent;


    private Task mTask;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected String getToolBarTitle() {
        return "任务详情";
    }


    @Override
    protected void initLocalData() {
        Intent intent = getIntent();

        mTask = (Task) intent.getSerializableExtra("Task");
    }


    @Override
    protected void initView() {
        tvContent.setText(mTask.getTaskContent());
        tvStartTime.setText(DateUtil.getStringTime(mTask.getStartTime(),sdf));
        tvEndTime.setText(DateUtil.getStringTime(mTask.getEndTime(),sdf));
        tvFuzenren.setText(mTask.getUserName());
        tvYouxianji.setText(mTask.getYouxianji());
    }
}
