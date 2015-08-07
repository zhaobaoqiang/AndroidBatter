package com.wudoumi.battertest.bean;

import com.j256.ormlite.field.DatabaseField;
import com.wudoumi.batter.utils.DateUtil;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class User {


    @DatabaseField(generatedId = true)
    private int _id;

    @DatabaseField
    private String name;

    @DatabaseField
    private long millis;
    @DatabaseField
    private Date date;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public User() {
        super();
        long millis = System.currentTimeMillis();
        this.date = new Date(millis);
        this.millis = millis;
    }

    public User(String name) {
        this();
        this.name = name;
    }


    public String getTime(){
        return DateUtil.getInstance().getStringTime(millis);
    }

}
