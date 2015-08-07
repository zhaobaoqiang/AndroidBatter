package cn.fetech.sanyi.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.wudoumi.batter.exception.BatterExcetion;
import com.wudoumi.batter.net.ResponseListner;
import com.wudoumi.batter.utils.ToastUtil;

/**
 * Created by qianjujun on 2015/8/4 0004 16:08.
 * qianjujun@163.com
 */
public class SimpleProgress extends ResponseListner{

    private ProgressDialog p;

    private Context context;

    private String message;


    public SimpleProgress(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    @Override
    public void onStart() {
        super.onStart();

        p = ProgressDialog.show(context,"",message,true,true);

        p.setCanceledOnTouchOutside(false);

        p.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel();
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
    }

    @Override
    public void onError(BatterExcetion error) {
        ToastUtil.showToast(context,error.getMessage());
    }

    @Override
    public void onEnd() {
        super.onEnd();

        if(p!=null&&p.isShowing()){
            p.dismiss();
        }
    }
}
