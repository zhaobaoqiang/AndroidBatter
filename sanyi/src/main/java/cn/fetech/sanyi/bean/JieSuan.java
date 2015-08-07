package cn.fetech.sanyi.bean;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/7/30 0030 15:55.
 * qianjujun@163.com
 */
public class JieSuan implements Serializable{



    private int id;

    //客户id
    private int kuhuUserId;

    private long time;

    private double yingshou;

    private double yishou;

    private double weikuan;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKuhuUserId() {
        return kuhuUserId;
    }

    public void setKuhuUserId(int kuhuUserId) {
        this.kuhuUserId = kuhuUserId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getYingshou() {
        return yingshou;
    }

    public void setYingshou(double yingshou) {
        this.yingshou = yingshou;
    }

    public double getYishou() {
        return yishou;
    }

    public void setYishou(double yishou) {
        this.yishou = yishou;
    }

    public double getWeikuan() {
        return weikuan;
    }

    public void setWeikuan(double weikuan) {
        this.weikuan = weikuan;
    }
}
