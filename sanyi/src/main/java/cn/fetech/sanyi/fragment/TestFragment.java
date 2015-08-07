package cn.fetech.sanyi.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/6 0006 15:06.
 * qianjujun@163.com
 */
public class TestFragment extends BatterFragment{

    @ViewInject(R.id.text)
    TextView tv;

    String text;
    public static TestFragment instance(String text){
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text",text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("text");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initData() {
        super.initData();
        tv.setText(text);
    }
}
