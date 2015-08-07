package cn.fetech.sanyi.bean;

import java.text.DecimalFormat;

/**
 * Created by qianjujun on 2015/7/3 0003 11:01.
 * qianjujun@163.com
 */
public class CustomUser extends User {

    private static DecimalFormat df = new DecimalFormat("######0.00");

    private double monthTurnVolume;

    private double totalTurnVolume;


    public double getMonthTurnVolume() {
        return monthTurnVolume;
    }

    public void setMonthTurnVolume(double monthTurnVolume) {
        this.monthTurnVolume = monthTurnVolume;
    }

    public double getTotalTurnVolume() {
        return totalTurnVolume;
    }

    public void setTotalTurnVolume(double totalTurnVolume) {
        this.totalTurnVolume = totalTurnVolume;
    }

    @Override
    public String getTopTips() {
        return "本月成交："+getMonthTurnVolume()+"万";
    }

    @Override
    public String getBottomTips() {
        return "累计成交："+getTotalTurnVolume()+"万";
    }


    @Override
    public int getUserType() {
        return 4;
    }



}
