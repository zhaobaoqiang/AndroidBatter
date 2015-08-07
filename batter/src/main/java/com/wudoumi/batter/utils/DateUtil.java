package com.wudoumi.batter.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class DateUtil {

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private SimpleDateFormat sdfYmdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ");
    private DateUtil(){

    }

    private static DateUtil instance;

    public static DateUtil getInstance(){
        if(instance == null){
            synchronized (DateUtil.class){
                if(instance==null){
                    instance = new DateUtil();
                }
            }
        }
        return instance;
    }


    public String getHMS(long time){
        return sdf.format(time);
    }


    public String getStringTime(long time){
        return sdfYmdHms.format(time);
    }



    public static String getStringTime(long time,SimpleDateFormat sdf){
        return sdf.format(time);
    }




    public static String getAlmostTime(long time){

        return null;
    }


    public static long[] getCurrentDayStartAndEnd(){
        long[] times = new long[2];
        Calendar calendar = Calendar.getInstance();

        times[0] = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        times[1] = calendar.getTimeInMillis();
        return times;

    }

}
