package cn.fetech.sanyi.bean;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/7/29 0029 15:16.
 * qianjujun@163.com
 */
public class Zhanting implements Serializable{

    private int id;

    private String content;

    private String jiedaiUser;


    private String result;

    private long jiedaiTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJiedaiUser() {
        return jiedaiUser;
    }

    public void setJiedaiUser(String jiedaiUser) {
        this.jiedaiUser = jiedaiUser;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getJiedaiTime() {
        return jiedaiTime;
    }

    public void setJiedaiTime(long jiedaiTime) {
        this.jiedaiTime = jiedaiTime;
    }
}
