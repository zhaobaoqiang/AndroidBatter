package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qianjujun on 2015/7/28 0028 16:06.
 * qianjujun@163.com
 */
public class WeekDateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");

    private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");


    public static void handlerDate(WeekDate weekDate,boolean next){
        Date[] dates = null;
        if(next){
            weekDate.setCurrentWeek(weekDate.getNextWeek());
            weekDate.setCurrentYear(weekDate.getNextYear());
            weekDate.setMaxWeek(weekDate.getNextYearMaxWeek());
            dates = getDates(weekDate.getCalendar(),weekDate.getNextWeek(),weekDate.getNextYear());
        }else{
            weekDate.setMaxWeek(weekDate.getPreYearMaxWeek());
            weekDate.setCurrentWeek(weekDate.getPreWeek());
            weekDate.setCurrentYear(weekDate.getPreYear());
            dates = getDates(weekDate.getCalendar(),weekDate.getPreWeek(),weekDate.getPreYear());
        }
        weekDate.setCurrentDate(sdfMonth.format(weekDate.getCalendar().getTime())+"\t"+"第"+weekDate.getCurrentWeek()+"周");
        weekDate.setCurrentWeekDates(dates);
        weekDate.update();
        System.out.println(weekDate);
    }

    public static WeekDate getWeekDate(Calendar calendar){
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2015);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);

        System.out.println(sdf.format(calendar.getTime()));
        System.out.println("---------------");

        WeekDate weekDate = new WeekDate();
        weekDate.setCurrentYear(calendar.get(Calendar.YEAR));

        weekDate.update();
        weekDate.setCurrentWeekDates(getDates(calendar,weekDate.getCurrentWeek(),weekDate.getCurrentYear()));


        System.out.println(weekDate);
        return weekDate;
    }



    public static Date[] getDates(Calendar calendar,int weekOfyear,int year){
        Date[] dates = new Date[7];
        calendar.set(Calendar.WEEK_OF_YEAR,weekOfyear);
        calendar.set(Calendar.YEAR,year);
        setToFirstDay(calendar);

        for (int i = 0; i < 7; i++) {
            dates[i] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);

            //System.out.println(sdf.format(dates[i]));
        }
        return dates;
    }


    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }



    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }



    public static void main(String[] args){
//        Calendar calendar = Calendar.getInstance();
//
//        WeekDate weekDate = getWeekDate(calendar);
//
//        //System.out.println(weekDate);
//
//        handlerDate(calendar,weekDate,false);
//
//        //handlerDate(weekDate,true);
//
//        //handlerDate(weekDate,false);
//        //handlerDate(weekDate,false);
//        //handlerDate(weekDate,false);
//
//        //System.out.println(weekDate);



        WeekDate weekDate = new WeekDate();

        System.out.println(weekDate);


        //handlerDate();
    }
}
