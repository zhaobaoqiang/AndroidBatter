package cn.fetech.sanyi.bean;

/**
 * Created by qianjujun on 2015/7/30 0030 14:10.
 * qianjujun@163.com
 */
public class Fuwu {

    public static final int TYPE_CUSTOM = 0;

    public static final int TYPE_SUPPLIER = 1;

    private int id;


    private int type;

    private String title;

    private String state;

    private String content;

    private long time;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
