package cn.fetech.sanyi.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.wheel.OnWheelChangedListener;
import com.wudoumi.batter.view.wheel.OnWheelScrollListener;
import com.wudoumi.batter.view.wheel.WheelView;
import com.wudoumi.batter.view.wheel.adapter.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/13 0013 15:58.
 * qianjujun@163.com
 */
public class DateWheelPop extends WheelPop {
    @ViewInject(R.id.ok)
    private Button ok;

    @ViewInject(R.id.wheel_year)
    private WheelView wheelViewYear;

    @ViewInject(R.id.wheel_month)
    private WheelView wheelViewMonth;

    @ViewInject(R.id.wheel_day)
    private WheelView wheelViewDay;


    private int year;
    private int month;
    private int day;

    private boolean scollYear;
    private boolean scollMonth;
    private boolean scollDay;

    private DateAdapter dayAdapter;

    public DateWheelPop(Context context, OnSelectComplete onSelectComplete) {
        super(context, onSelectComplete);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.pop_date_wheel;
    }

    @Override
    protected void initLocalData() {
        super.initLocalData();
        days = new ArrayList<>();
    }

    @Override
    protected void initView(View view) {
        initYear();
        initMonth();
        initDay();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!scollDay && !scollMonth && !scollYear) {
                    onSelectComplete.onChoose(year + "年" + month + "月" + day + "日");
                    dismiss();
                } else {
                    ToastUtil.showToast(context, "请稍候...");
                }
            }
        });
    }


    private void initYear() {
        wheelViewYear.setVisibleItems(5);
        wheelViewYear.setViewAdapter(new DateAdapter(context, years));
        wheelViewYear.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scollYear = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                scollYear = false;
                if (month == 2) {
                    updateDay(years.get(wheelViewYear.getCurrentItem()), 2);
                }

            }
        });

        wheelViewYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                year = years.get(newValue);
                if (!scollYear&&month == 2) {
                    updateDay(year, 2);
                }
            }
        });

        wheelViewYear.setCurrentItem(years.size() / 2);
    }

    private void initMonth() {
        wheelViewMonth.setVisibleItems(5);
        wheelViewMonth.setViewAdapter(new DateAdapter(context, monthes));
        wheelViewMonth.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scollMonth = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                scollMonth = false;
                updateDay(years.get(wheelViewYear.getCurrentItem()), monthes.get(wheelViewMonth.getCurrentItem()));
            }
        });

        wheelViewMonth.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                month = monthes.get(newValue);
                if(!scollMonth){
                    updateDay(year, month);
                }

            }
        });

        wheelViewMonth.setCurrentItem(monthes.size() / 2);
    }

    private void initDay() {
        wheelViewDay.setVisibleItems(5);
        wheelViewDay.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scollDay = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                scollDay = false;

            }
        });
        wheelViewDay.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                day = days.get(wheel.getCurrentItem());
            }
        });
    }


    private static List<Integer> years = new ArrayList<>();

    private static List<Integer> monthes = new ArrayList<>();


    private List<Integer> days;

    private static Calendar calendar = Calendar.getInstance();

    static {
        for (int i = 1930; i < calendar.get(Calendar.YEAR); i++) {
            years.add(i);
        }

        for (int i = 1; i <= 12; i++) {
            monthes.add(i);
        }
    }


    private void updateDay(int year, int month) {
        int max = 30;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                max = 31;
                break;
            case 2:
                max = isYun(year) ? 29 : 28;
                break;
            default:
                max = 30;
                break;
        }
        days.clear();

        for (int i = 1; i <= max; i++) {
            days.add(i);
        }

        dayAdapter = new DateAdapter(context, days);
        wheelViewDay.setViewAdapter(dayAdapter);
        wheelViewDay.setCurrentItem(days.size() / 2);
        day = days.get(wheelViewDay.getCurrentItem());

    }


    private boolean isYun(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    class DateAdapter extends AbstractWheelTextAdapter {
        List<Integer> list;

        protected DateAdapter(Context context, List<Integer> list) {
            super(context, R.layout.item_pop_sex, R.id.text);
            this.list = list;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }
    }


}
