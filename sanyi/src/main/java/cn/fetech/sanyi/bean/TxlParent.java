package cn.fetech.sanyi.bean;

/**
 * Created by qianjujun on 2015/7/20 0020 11:23.
 * qianjujun@163.com
 */
public class TxlParent {
    private int icon;

    private int id;

    private int parentId;

    private String name;

    private int totalPeopleNum;


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPeopleNum() {
        return totalPeopleNum;
    }

    public void setTotalPeopleNum(int totalPeopleNum) {
        this.totalPeopleNum = totalPeopleNum;
    }
}
