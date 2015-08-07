package cn.fetech.sanyi.bean;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/8/5 0005 17:51.
 * qianjujun@163.com
 */
public class BaseBean implements Serializable{

    private int _id;

    private int id;


    private int userId = 1;

    private String userName = "登录人";


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
