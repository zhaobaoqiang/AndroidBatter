package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by qianjujun on 2015/7/28 0028 15:19.
 * qianjujun@163.com
 */
public class TestDate {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");

    public static void main(String[] args){
        //getDates(1,2014);

        System.out.println(getMaxWeekNumOfYear(2014));

        getDates(52,2014);
        System.out.println("---------------");
        getDates(1,2015);

    }


    public static Date[] getDates(int weekOfyear,int year){
        Date[] dates = new Date[7];
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR,weekOfyear);
        calendar.set(Calendar.YEAR,year);
        setToFirstDay(calendar);

        for (int i = 0; i < 7; i++) {
            dates[i] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);

            System.out.println(sdf.format(dates[i]));
        }
        return dates;
    }


    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }


    private static int jiaozheng(int weekOfYear){
        Calendar calendar = Calendar.getInstance();



        return 0;
    }


    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }



    static class WeekData{
        private int currentYear;

        private int currentWeek;

        private int nextYear;

        private int preYear;

        private int nextWeek;

        private int preWeek;

        private Date[] currentWeekDates;

        private String currentDate;


        public int getCurrentYear() {
            return currentYear;
        }

        public void setCurrentYear(int currentYear) {
            this.currentYear = currentYear;
        }

        public int getCurrentWeek() {
            return currentWeek;
        }

        public void setCurrentWeek(int currentWeek) {
            this.currentWeek = currentWeek;
        }

        public int getNextYear() {
            return nextYear;
        }

        public void setNextYear(int nextYear) {
            this.nextYear = nextYear;
        }

        public int getPreYear() {
            return preYear;
        }

        public void setPreYear(int preYear) {
            this.preYear = preYear;
        }

        public int getNextWeek() {
            return nextWeek;
        }

        public void setNextWeek(int nextWeek) {
            this.nextWeek = nextWeek;
        }

        public int getPreWeek() {
            return preWeek;
        }

        public void setPreWeek(int preWeek) {
            this.preWeek = preWeek;
        }

        public Date[] getCurrentWeekDates() {
            return currentWeekDates;
        }

        public void setCurrentWeekDates(Date[] currentWeekDates) {
            this.currentWeekDates = currentWeekDates;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public void setCurrentDate(String currentDate) {
            this.currentDate = currentDate;
        }
    }
}
