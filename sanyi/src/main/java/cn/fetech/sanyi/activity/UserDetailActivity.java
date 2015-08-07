package cn.fetech.sanyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.DateUtil;
import com.wudoumi.batter.utils.ToastUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.data.Constant;
import cn.fetech.sanyi.db.DBHelper;
import cn.fetech.sanyi.db.Regions;
import cn.fetech.sanyi.widget.AddressWheelPop;
import cn.fetech.sanyi.widget.DateWheelPop;
import cn.fetech.sanyi.widget.OnSelectComplete;
import cn.fetech.sanyi.widget.SexWheelPop;

public class UserDetailActivity extends BaseActivity {


    @ViewInject(R.id.user_name)
    private EditText etName;
    @ViewInject(R.id.phone)
    private EditText etPhone;
    @ViewInject(R.id.qq)
    private EditText etQQ;
    @ViewInject(R.id.weixin)
    private EditText etWeixin;
    @ViewInject(R.id.address_detail)
    private EditText etAddressDetail;
    @ViewInject(R.id.occupation)
    private EditText etOccupation;
    @ViewInject(R.id.avocation)
    private EditText etAvocation;
    @ViewInject(R.id.family)
    private EditText etFamily;
    @ViewInject(R.id.demand)
    private EditText etDemand;

    @ViewInject(R.id.sex)
    private TextView tvSex;
    @ViewInject(R.id.address)
    private TextView tvAddress;
    @ViewInject(R.id.birthday)
    private TextView tvBirthday;
    @ViewInject(R.id.from)
    private TextView tvFrom;
    @ViewInject(R.id.nature)
    private TextView tvNature;
    @ViewInject(R.id.introduce)
    private TextView tvIntroduce;

    @ViewInject(R.id.root)
    private LinearLayout root;






    private SexWheelPop sexWheelPop;

    private DateWheelPop dateWheelPop;

    private AddressWheelPop addressPop;




    public void performClick(View view){
        switch (view.getId()){
            case R.id.sex:
                if(sexWheelPop==null){
                    sexWheelPop = new SexWheelPop(this, new SimpleOnSelectComplete(tvSex));
                }
                sexWheelPop.show();
                break;

            case R.id.birthday:
                if(dateWheelPop==null){
                    dateWheelPop = new DateWheelPop(this, new SimpleOnSelectComplete(tvBirthday));
                }
                dateWheelPop.show();
                break;

            case R.id.address:
                if(addressPop==null){
                    addressPop = new AddressWheelPop(this, new SimpleOnSelectComplete(tvAddress));
                }
                addressPop.show();
                break;

            case R.id.introduce:
                selectUser();
                break;
        }
    }


    private void selectUser(){
        Intent intent = new Intent(this,InnerTxlActivity.class);
        intent.putExtra("order", Constant.SECLCT_USER_USERDETAIL_ORDER);
        startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent!=null&&intent.getIntExtra("order",0)==Constant.SECLCT_USER_USERDETAIL_ORDER){
            String name = intent.getStringExtra("userName");
            tvIntroduce.setText(name);
        }
    }

    class SimpleOnSelectComplete implements OnSelectComplete<String>{
        private final TextView tv;
        public SimpleOnSelectComplete(TextView tv) {
            this.tv = tv;
        }

        @Override
        public void onChoose(String result) {
            tv.setText(result);
        }

        @Override
        public View getRootView() {
            return root;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected String getToolBarTitle() {
        return "个人资料";
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        ToastUtil.showToast(this,"保存资料");
        return true;
    }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }



    private void initEditAble(boolean editAble) {

        etName.setEnabled(editAble);
        etPhone.setEnabled(editAble);
        etQQ.setEnabled(editAble);
        etWeixin.setEnabled(editAble);
        etAddressDetail.setEnabled(editAble);
        etOccupation.setEnabled(editAble);
        etAvocation.setEnabled(editAble);
        etFamily.setEnabled(editAble);
        etDemand.setEnabled(editAble);
    }
}
