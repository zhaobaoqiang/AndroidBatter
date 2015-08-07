package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.StringUtil;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/8/4 0004 11:13.
 * qianjujun@163.com
 */
public class Qingjiadan implements Serializable{
    private int id;

    private String title;

    private String state;

    private String content;

    private long startTime;

    private long endTime;

    private long createTime;

    private String time;

    private int userId;

    private int reciverId;

    private String reciverName;


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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        if(StringUtil.isEmpty(time)){
            long chaTime = endTime-startTime;
            time = getTimeStr(chaTime);
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    private String getTimeStr(long time){
        int hour = (int) (time/1000l/3600l);

        if(hour<24){
            return hour+"小时";
        }

        return hour/24+"天";

    }
}
