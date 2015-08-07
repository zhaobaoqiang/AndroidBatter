package cn.fetech.sanyi.fragment;


import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.data.WeekDate;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekFragment extends BatterFragment implements View.OnClickListener{

    @ViewInject(R.id.week0)
    private TextView week0;

    @ViewInject(R.id.week1)
    private TextView week1;

    @ViewInject(R.id.week2)
    private TextView week2;

    @ViewInject(R.id.week3)
    private TextView week3;

    @ViewInject(R.id.week4)
    private TextView week4;

    @ViewInject(R.id.week5)
    private TextView week5;

    @ViewInject(R.id.week6)
    private TextView week6;

    @ViewInject(R.id.time)
    private TextView tvTime;

    @ViewInject(R.id.weekRight)
    private ImageButton ibNext;

    @ViewInject(R.id.weekLeft)
    private ImageButton ibPre;


    private List<TextView> textViews = new ArrayList<>();


    private OnDateChangeListner onDateChangeListner;


    private WeekDate weekDate;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd");

    public WeekFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {

        return R.layout.fragment_week;
    }


    @Override
    protected void initData() {
        weekDate = new WeekDate();

        textViews.add(week0);
        textViews.add(week1);
        textViews.add(week2);
        textViews.add(week3);
        textViews.add(week4);
        textViews.add(week5);
        textViews.add(week6);



        for(TextView tv : textViews){
            tv.setOnClickListener(this);
        }


        update(weekDate);


        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekDate.nextWeek();
                update(weekDate);
            }
        });

        ibPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekDate.preWeek();
                update(weekDate);
            }
        });
    }

    private void update(WeekDate weekDate){
        for(int i = 0;i<textViews.size();i++){
            textViews.get(i).setText(sdf.format(weekDate.getCurrentWeekDates()[i]));
            textViews.get(i).setTag(weekDate.getCurrentWeekDates()[i].getTime());
        }

        tvTime.setText(weekDate.getCurrentDate());
    }



    @Override
    public void onClick(View v) {
        if(v.isSelected()){
            return;
        }
        for(TextView tv: textViews){
            tv.setSelected(tv==v);
        }


        if(onDateChangeListner!=null){
            onDateChangeListner.onDateChange(Long.parseLong(v.getTag().toString()));
        }
    }



    public void setOnDateChangeListner(OnDateChangeListner onDateChangeListner){
        this.onDateChangeListner = onDateChangeListner;
    }


    public interface OnDateChangeListner{
        void onDateChange(long newDate);
    }




}
