package cn.fetech.sanyi.bean;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/7/27 0027 13:43.
 * qianjujun@163.com
 */
public class TxlUser implements Serializable{

    private int _id;

    private int id;

    private String name;

    private String phone;

    private String faceUrl;


    private int role;

    private String roleName;


    public TxlUser(int role, String roleName) {
        this.role = role;
        this.roleName = roleName;
    }

    public TxlUser(String roleName) {
        this.roleName = roleName;
    }


    public TxlUser() {

    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }
}
