package com.wudoumi.batter.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public class ToastUtil {
    public static void showToast(Context context,CharSequence msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context,int msgRes){
        Toast.makeText(context,msgRes,Toast.LENGTH_SHORT).show();
    }
}
