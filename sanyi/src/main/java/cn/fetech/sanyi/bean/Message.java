package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.DateUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by qianjujun on 2015/7/27 0027 15:49.
 * qianjujun@163.com
 */
public class Message implements Serializable{

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private int id;

    private String title;

    private String content;

    private long time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public String getTimeStr(){
        if(time==0){
            return "未知时间";
        }
        return DateUtil.getStringTime(time,sdf);
    }


    
}
