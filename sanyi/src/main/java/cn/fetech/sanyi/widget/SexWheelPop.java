package cn.fetech.sanyi.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.wheel.OnWheelScrollListener;
import com.wudoumi.batter.view.wheel.WheelView;
import com.wudoumi.batter.view.wheel.adapter.AbstractWheelTextAdapter;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/13 0013 14:19.
 * qianjujun@163.com
 */
public class SexWheelPop extends WheelPop {

    @ViewInject(R.id.ok)
    private Button ok;

    @ViewInject(R.id.wheel)
    private WheelView wheelView;


    private final String[] sexes = new String[]{"男", "女"};


    private boolean enableOk = true;




    public SexWheelPop(Context context, OnSelectComplete onSelectComplete) {
        super(context, onSelectComplete);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.pop_sex_wheel;
    }

    @Override
    protected void initView(View view) {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableOk) {
                    onSelectComplete.onChoose(sexes[wheelView.getCurrentItem()]);
                    dismiss();
                } else {
                    ToastUtil.showToast(context, "请稍等...");
                }

            }
        });
        wheelView.setVisibleItems(2);
        wheelView.setViewAdapter(new SexAdapter(context));


        wheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                enableOk = false;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                enableOk = true;
            }
        });

    }


    class SexAdapter extends AbstractWheelTextAdapter {


        protected SexAdapter(Context context) {
            super(context, R.layout.item_pop_sex, R.id.text);
        }

        @Override
        protected CharSequence getItemText(int index) {
            return sexes[index];
        }

        @Override
        public int getItemsCount() {
            return sexes.length;
        }
    }


}
