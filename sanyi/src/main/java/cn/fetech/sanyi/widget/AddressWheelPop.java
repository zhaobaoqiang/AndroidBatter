package cn.fetech.sanyi.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.dao.Dao;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.wheel.OnWheelChangedListener;
import com.wudoumi.batter.view.wheel.OnWheelScrollListener;
import com.wudoumi.batter.view.wheel.WheelView;
import com.wudoumi.batter.view.wheel.adapter.AbstractWheelTextAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.db.DBHelper;
import cn.fetech.sanyi.db.Regions;

/**
 * Created by qianjujun on 2015/7/14 0014 14:32.
 * qianjujun@163.com
 */
public class AddressWheelPop extends WheelPop{
    private Dao<Regions,Integer> dao;
    private List<Regions> shengList;

    private List<Regions> shiList;

    @ViewInject(R.id.ok)
    private Button ok;

    @ViewInject(R.id.wheel_sheng)
    private WheelView wheelViewSheng;

    @ViewInject(R.id.wheel_shi)
    private WheelView wheelViewShi;


    private boolean scollSheng;

    private boolean scollShi;


    private Regions sheng;

    private Regions shi;

    public AddressWheelPop(Context context, OnSelectComplete onSelectComplete) {
        super(context, onSelectComplete);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.pop_address_wheel;
    }

    @Override
    protected void initView(View view) {
        initSheng();
        initShi();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!scollSheng&&!scollShi){
                    String result = "未知";
                    if(sheng!=null&&shi!=null){
                        result = sheng.getName()+shi.getName();
                    }
                    onSelectComplete.onChoose(result);

                    dismiss();

                }else{
                    ToastUtil.showToast(context,"请稍后...");
                }
            }
        });
    }

    private void initSheng() {
        wheelViewSheng.setVisibleItems(5);
        wheelViewSheng.setViewAdapter(new AddressAdapter(context, shengList));
        wheelViewSheng.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scollSheng = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                scollSheng = false;
                updateShi(shengList.get(wheel.getCurrentItem()));

            }
        });

        wheelViewSheng.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                sheng = shengList.get(newValue);
                updateShi(sheng);
            }
        });

        wheelViewSheng.setCurrentItem(shengList.size() / 2);
    }

    private void initShi() {
        wheelViewShi.setVisibleItems(5);
        wheelViewShi.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scollShi = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                scollShi = false;

            }
        });
        wheelViewShi.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                shi = shiList.get(wheel.getCurrentItem());
            }
        });
    }


    private void updateShi(Regions regions){

        try {
            List<Regions> temp = dao.queryForEq("parent_id", regions.getId());
            if(temp!=null&&temp.size()>0){
                shiList.clear();
                shiList.addAll(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        wheelViewShi.setViewAdapter(new AddressAdapter(context,shiList));
        wheelViewShi.setCurrentItem(shiList.size()/2);

        shi = shiList.get(wheelViewShi.getCurrentItem());

    }

    @Override
    protected void initLocalData() {
        super.initLocalData();
        shengList = new ArrayList<>();
        shiList = new ArrayList<>();
        try {
            this.dao = DBHelper.getInstance().getDao(Regions.class);
            List<Regions> temp = dao.queryForEq("level", 1);
            if(temp!=null&&temp.size()>0){
                shengList.clear();
                shengList.addAll(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        if(dao!=null){
            super.show();
        }else{
            ToastUtil.showToast(context,"地址数据库读取失败");
        }
    }


    class AddressAdapter extends AbstractWheelTextAdapter {
        List<Regions> list;

        protected AddressAdapter(Context context, List<Regions> list) {
            super(context, R.layout.item_pop_sex, R.id.text);
            this.list = list;
        }

        @Override
        protected CharSequence getItemText(int index) {

            return list.get(index).getName();
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }
    }
}
