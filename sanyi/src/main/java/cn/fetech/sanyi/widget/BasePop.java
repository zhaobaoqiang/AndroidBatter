package cn.fetech.sanyi.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by qianjujun on 2015/7/22 0022 15:18.
 * qianjujun@163.com
 */
public class BasePop extends PopupWindow{


    public BasePop(Context context,int layoutId){
        View contentView = LayoutInflater.from(context).inflate(layoutId,null);
        init(contentView);
    }

    public BasePop(View contentView){

        init(contentView);
    }

    private void init(View contentView){
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        setFocusable(true);

        setOutsideTouchable(true);

        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }



}
