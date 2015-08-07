package cn.fetech.sanyi.activity;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.DateUtil;
import com.wudoumi.batter.utils.StringUtil;
import com.wudoumi.batter.utils.ToastUtil;

import java.text.SimpleDateFormat;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.FollowUp;
import cn.fetech.sanyi.bean.TxlUser;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.widget.DateTimePop;
import cn.fetech.sanyi.widget.OnSelectComplete;
import cn.fetech.sanyi.widget.SingleWheelPop;

/**
 * 添加跟进
 */
public class AddFollowUpActivity extends BaseActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @ViewInject(R.id.cb_yuyue)
    private CheckBox cbYuyue;

    @ViewInject(R.id.yuyue_root)
    private ViewStub viewStub;

    @ViewInject(R.id.editText)
    private EditText etContent;

    @ViewInject(R.id.tv_follow_time)
    private TextView tvGenjinTime;

    @ViewInject(R.id.tv_lianxifangshi)
    private TextView tvLianxi;

    @ViewInject(R.id.tv_time_huifang)
    private TextView tvNextHuifang;





    @ViewInject(R.id.root)
    private LinearLayout root;


    private View viewYuyue;

    private TextView tvCanguanTime;

    private TextView tvJiedairen;

    private DateTimePop genjinPop;
    private DateTimePop huifangPop;
    private DateTimePop canguanPop;

    private SingleWheelPop lianxiPop;


    /**
     * 接待人，不是登录用户
     */
    private TxlUser user;



    private FollowUp followUp;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_follow_up;
    }

    @Override
    protected String getToolBarTitle() {

        return title;
    }

    String title;

    @Override
    protected void initLocalData() {
        Intent intent = getIntent();

        title = intent.getStringExtra("title");

        if(StringUtil.isEmpty(title)){
            title = "写跟进";
        }
    }

    @Override
    protected void initView() {
        cbYuyue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchYuyue(isChecked);
            }
        });

        followUp = new FollowUp();

        long time = System.currentTimeMillis();

        setDate(tvGenjinTime,time);
        setDate(tvNextHuifang,time);

        followUp.setTime(time);
        followUp.setNextFollowTime(time);



    }

    private void setDate(TextView tv,long time){
        tv.setText(sdf.format(time));
    }

    private void switchYuyue(boolean flag){
        followUp.setHaveYuyue(flag);
        if(flag){
            if(viewYuyue==null){
                viewYuyue = viewStub.inflate();
                tvCanguanTime = (TextView) viewYuyue.findViewById(R.id.tv_time_canguan);
                tvJiedairen = (TextView) viewYuyue.findViewById(R.id.tv_jiedairen);
                long time = System.currentTimeMillis();
                setDate(tvCanguanTime,time);
                followUp.setCanguanTime(time);
            }else{
                viewYuyue.setVisibility(View.VISIBLE);
            }
        }else{
            if(viewYuyue!=null){
                viewYuyue.setVisibility(View.GONE);
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_follow_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        if (id == R.id.sure) {
            followUp.setContent(etContent.getText().toString());
            String result = followUp.check();
            if(result!=null){
                ToastUtil.showToast(this,result);
            }else{
                ToastUtil.showToast(this,"准备保存");
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void performClick(View view){
        switch (view.getId()){
            case R.id.ll_genjinTime:
                if(genjinPop==null){
                    genjinPop = new DateTimePop(this,new SimpleOnSelectComplete(tvGenjinTime){
                        @Override
                        public void onChoose(Long result) {
                            super.onChoose(result);
                            followUp.setTime(result);
                        }
                    });
                }
                genjinPop.show();
                break;

            case R.id.ll_canguanTime:
                if(canguanPop==null){
                    canguanPop = new DateTimePop(this,new SimpleOnSelectComplete(tvCanguanTime){
                        @Override
                        public void onChoose(Long result) {
                            super.onChoose(result);
                            followUp.setCanguanTime(result);
                        }
                    });
                }
                canguanPop.show();
                break;
            case R.id.ll_huifangTime:
                if(huifangPop==null){
                    huifangPop = new DateTimePop(this,new SimpleOnSelectComplete(tvNextHuifang){
                        @Override
                        public void onChoose(Long result) {
                            super.onChoose(result);
                            followUp.setNextFollowTime(result);
                        }
                    });
                }
                huifangPop.show();
                break;

            case R.id.ll_lianxi:
                if(lianxiPop==null){
                    lianxiPop = new SingleWheelPop(this, new StringOnSelectComplete(tvLianxi), FollowUp.stateTypes);
                }
                lianxiPop.show();
                break;

            case R.id.tv_jiedairen:
                selectUser();
                break;
        }
    }


    private void selectUser(){
        Intent intent = new Intent(this,InnerTxlActivity.class);
        intent.putExtra("order", Constant.SECLCT_USER_FOLLOW_ORDER);
        startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent!=null&&intent.getIntExtra("order",0)==Constant.SECLCT_USER_FOLLOW_ORDER){

            user = (TxlUser) intent.getSerializableExtra("User");
            String name = user.getName();
            tvJiedairen.setText(name);

            followUp.setJiedairenId(user.getId());
        }
    }


    class SimpleOnSelectComplete implements OnSelectComplete<Long>{
        private final TextView tv;

        public SimpleOnSelectComplete(TextView tv) {
            this.tv = tv;
        }

        @Override
        public void onChoose(Long result) {

           // tv.setText(DateUtil.getStringTime(result,sdf));
            setDate(tv,result);
        }

        @Override
        public View getRootView() {
            return root;
        }
    }




    class StringOnSelectComplete implements OnSelectComplete<String>{
        private final TextView tv;

        public StringOnSelectComplete(TextView tv) {
            this.tv = tv;
        }

        @Override
        public void onChoose(String result) {
            tv.setText(result);

            followUp.setStateType(result);
        }

        @Override
        public View getRootView() {
            return root;
        }
    }
}
