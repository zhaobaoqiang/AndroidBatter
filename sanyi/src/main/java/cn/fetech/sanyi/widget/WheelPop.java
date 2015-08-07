package cn.fetech.sanyi.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wudoumi.batter.ioc.ViewUtils;


/**
 * Created by qianjujun on 2015/7/13 0013 14:13.
 * qianjujun@163.com
 */
public abstract class WheelPop extends PopupWindow{
    final protected OnSelectComplete onSelectComplete;
    final protected Context context;
    public WheelPop(Context context, OnSelectComplete onSelectComplete) {
        super(context);
        this.context = context;
        this.onSelectComplete = onSelectComplete;
        initLocalData();
        View view = LayoutInflater.from(context).inflate(getContentLayoutId(),null);
        ViewUtils.inject(this,view);
        initView(view);
        setContentView(view);
        setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(false);


    }


    protected abstract int getContentLayoutId();

    protected abstract void initView(View view);

    protected void initLocalData(){}


    public void show(){

        showAtLocation(onSelectComplete.getRootView(), Gravity.BOTTOM,0,0);
    }
}
