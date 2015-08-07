package cn.fetech.sanyi.bean;

/**
 * Created by qianjujun on 2015/7/8 0008 14:37.
 * qianjujun@163.com
 *
 *
 * 资源用户下关联的联系人
 */
public class ResourcesContacts extends User{

    private String relation;

    private int userId;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
