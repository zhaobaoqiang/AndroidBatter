package cn.fetech.sanyi.activity;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.StringUtil;
import com.wudoumi.batter.utils.ToastUtil;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.ResourcesContacts;


/***
 * 添加联系人
 */
public class AddContactsActivity extends BaseActivity {

    @ViewInject(R.id.user_name)
    private EditText etName;

    @ViewInject(R.id.phone)
    private EditText etPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_contacts;
    }

    @Override
    protected String getToolBarTitle() {
        return "添加联系人";
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
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
            handlerSave();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void handlerSave(){
        String name = etName.getText().toString();

        String phone = etPhone.getText().toString();

        if(StringUtil.isEmpty(name)){
            ToastUtil.showToast(this,"请输入姓名");
            return;
        }

        if(StringUtil.isEmpty(phone)){
            ToastUtil.showToast(this,"请输入手机");
            return;
        }


        ResourcesContacts user = new ResourcesContacts();
        user.setName(name);
        user.setPhone(phone);
        Intent data = new Intent();
        data.putExtra("User",user);
        setResult(RESULT_OK, data);
        ToastUtil.showToast(this,"保存成功");
        finish();
    }
}
