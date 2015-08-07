package cn.fetech.sanyi.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wudoumi.batter.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/22 0022 15:23.
 * qianjujun@163.com
 */
public class DateTimePop extends BasePop{
    private Calendar calendarResult = Calendar.getInstance();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm E");

    private final OnSelectComplete onSelectComplete;
    public DateTimePop(Context context, final OnSelectComplete onSelectComplete) {
        super(context,R.layout.pop_datetime);

        this.onSelectComplete = onSelectComplete;

        View cotentView = getContentView();

        final TextView tv = (TextView) cotentView.findViewById(R.id.tv_time);

        Calendar calendar = Calendar.getInstance();

        DatePicker datePicker = (DatePicker) cotentView.findViewById(R.id.datePicker);

        TimePicker timePicker = (TimePicker) cotentView.findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendarResult.set(year,monthOfYear,dayOfMonth);

                tv.setText(DateUtil.getStringTime(calendarResult.getTimeInMillis(),sdf));
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                calendarResult.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendarResult.set(Calendar.MINUTE,minute);
                tv.setText(DateUtil.getStringTime(calendarResult.getTimeInMillis(),sdf));
            }
        });




        cotentView.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = calendarResult.getTimeInMillis();
                onSelectComplete.onChoose(time);

                dismiss();
            }
        });

    }


    public void show(){
        showAtLocation(onSelectComplete.getRootView(), Gravity.NO_GRAVITY,0,0);
    }




}
