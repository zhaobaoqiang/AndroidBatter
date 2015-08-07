package cn.fetech.sanyi.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.wheel.OnWheelScrollListener;
import com.wudoumi.batter.view.wheel.WheelView;
import com.wudoumi.batter.view.wheel.adapter.AbstractWheelTextAdapter;

import java.util.List;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/27 0027 09:32.
 * qianjujun@163.com
 */
public class SingleWheelPop extends WheelPop{

    @ViewInject(R.id.ok)
    private Button ok;

    @ViewInject(R.id.wheel)
    private WheelView wheelView;



    private boolean enableOk = true;


    private List<String> list;

    public SingleWheelPop(Context context, OnSelectComplete onSelectComplete,List<String> list) {
        super(context, onSelectComplete);
        this.list = list;

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.pop_single_wheel;
    }

    @Override
    protected void initView(View view) {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableOk) {
                    onSelectComplete.onChoose(list.get(wheelView.getCurrentItem()));
                    dismiss();
                } else {
                    ToastUtil.showToast(context, "请稍等...");
                }

            }
        });
        wheelView.setVisibleItems(5);
        wheelView.setViewAdapter(new SingleAdapter(context));


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


    class SingleAdapter extends AbstractWheelTextAdapter {

        protected SingleAdapter(Context context) {
            super(context, R.layout.item_pop_sex, R.id.text);
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index);
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }
    }




}
