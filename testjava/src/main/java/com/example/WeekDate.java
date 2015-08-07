package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qianjujun on 2015/7/28 0028 16:05.
 * qianjujun@163.com
 */
public class WeekDate {
    private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");

    private int currentYear;

    private int currentWeek;

    private int nextYear;

    private int preYear;

    private int nextWeek;

    private int preWeek;

    private Date[] currentWeekDates;

    private String currentDate;

    private int maxWeek;

    private int nextYearMaxWeek;

    private int preYearMaxWeek;





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

    public int getMaxWeek() {
        return maxWeek;
    }

    public void setMaxWeek(int maxWeek) {
        this.maxWeek = maxWeek;
    }

    public int getNextYearMaxWeek() {
        return nextYearMaxWeek;
    }

    public void setNextYearMaxWeek(int nextYearMaxWeek) {
        this.nextYearMaxWeek = nextYearMaxWeek;
    }

    public int getPreYearMaxWeek() {
        return preYearMaxWeek;
    }

    public void setPreYearMaxWeek(int preYearMaxWeek) {
        this.preYearMaxWeek = preYearMaxWeek;
    }


    private Map<Integer,Integer> map = new HashMap<>();

    public void update(){
        currentWeekDates = getDates(calendar, currentWeek, currentYear);
        currentDate = sdfMonth.format(calendar.getTime())+"\t"+"第"+currentWeek+"周";

        if(currentWeek==maxWeek){
            nextYear = currentYear+1;
            nextWeek = 1;
            nextYearMaxWeek = getMaxWeekOfYear(nextYear);

            preYear = currentYear;
            preWeek = currentWeek-1;
            preYearMaxWeek = maxWeek;
        }else if(currentWeek==1){
            nextYear = currentYear;
            nextWeek = currentWeek+1;
            nextYearMaxWeek = maxWeek;

            preYear = currentYear-1;
            preYearMaxWeek = getMaxWeekOfYear(preYear);
            preWeek = preYearMaxWeek;
        }else{
            nextYear = currentYear;
            nextWeek = currentWeek+1;
            nextYearMaxWeek = maxWeek;

            preYear = currentYear;
            preWeek = currentWeek-1;
            preYearMaxWeek = maxWeek;
        }
    }


    private Calendar calendar;


    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        currentYear = calendar.get(Calendar.YEAR);
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        maxWeek = getMaxWeekOfYear(currentYear);

        if(calendar.get(Calendar.MONTH)==Calendar.DECEMBER&&currentWeek==1){//微调
            currentWeek = maxWeek;
        }


        update();
    }

    public WeekDate(){
        setCalendar(Calendar.getInstance());
    }

    public WeekDate(Calendar calendar){
       setCalendar(calendar);
    }


    /**
     * 获取一年总周数
     * @param year
     * @return
     */
    private int getMaxWeekOfYear(int year){
        if(!map.containsKey(year)){
            int max = getMaxWeekNumOfYear(year);
            map.put(year,max);
        }
        return map.get(year);
    }


    private int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    private int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }


    public void nextWeek(){
        currentYear = nextYear;
        currentWeek = nextWeek;
        maxWeek = nextYearMaxWeek;
        update();
    }


    public void preWeek(){
        currentYear = preYear;
        currentWeek = preWeek;
        maxWeek = preYearMaxWeek;
        update();
    }




    @Override
    public String toString() {
        get();
        return "WeekDate{" +
                "currentYear=" + currentYear +
                ", currentWeek=" + currentWeek +
                ", nextYear=" + nextYear +
                ", preYear=" + preYear +
                ", nextWeek=" + nextWeek +
                ", preWeek=" + preWeek +
                ", currentWeekDates="  +
                ", currentDate='" + currentDate + '\'' +
                ", maxWeek=" + maxWeek +
                ", nextYearMaxWeek=" + nextYearMaxWeek +
                ", preYearMaxWeek=" + preYearMaxWeek +
                ", map=" + map +
                '}';
    }

    private void get(){

        for(Date date : currentWeekDates){
            System.out.println(sdf.format(date));
        }
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");






    private Date[] getDates(Calendar calendar,int weekOfyear,int year){
        Date[] dates = new Date[7];
        calendar.set(Calendar.WEEK_OF_YEAR,weekOfyear);
        calendar.set(Calendar.YEAR,year);
        setToFirstDay(calendar);

        for (int i = 0; i < 7; i++) {
            dates[i] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }


    private void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }




    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.DECEMBER, 31, 23, 59, 59);

        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));

        System.out.println(calendar.get(Calendar.YEAR));


        WeekDate weekDate = new WeekDate(calendar);


        System.out.println(weekDate.getWeekOfYear(calendar.getTime()));

        System.out.println(weekDate);

        weekDate.nextWeek();

       System.out.println(weekDate);
    }
}
