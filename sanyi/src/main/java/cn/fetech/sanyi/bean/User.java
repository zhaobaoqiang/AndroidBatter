package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.ChineseToSpell;
import com.wudoumi.batter.utils.StringUtil;
import com.wudoumi.batter.view.listview.listviewfilter.IAutoConvertLetter;
import com.wudoumi.batter.view.listview.listviewfilter.ISerach;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by qianjujun on 2015/7/3 0003 10:53.
 * qianjujun@163.com
 */
public class User implements IAutoConvertLetter, ISerach, Serializable {


    protected static Random random = new Random();

    /**
     * 潜在客户
     */
    public static final int TYPE_USER_POTENTIAL = 1;

    /**
     * 意向客户
     */
    public static final int TYPE_USER_INTENTION = 2;

    /**
     * 准客户
     */
    public static final int TYPE_USER_QUASI = 3;

    /**
     * 正式客户
     */
    public static final int TYPE_USER_FORMAL = 4;



    public static final int TYPE2_PRIVATE = 1;

    public static final int TYPE2_PUBLIC = 2;

    public static final int TYPE2_CUSTOM = 3;

    public static final int TYPE2_SUPPLIER = 4;

//    public static final int TYPE2_RESOURCES_CONTACTS = 5;
//
//    public static final int TYPE2_INNER_CONTACTS = 6;



    private int _id;

    private int id;

    private String name;

    private String phone;

    private String faceUrl;

    protected int userType;

    protected int userType2;


    private boolean titleItem;//接口字段

    private String firstLetter;//接口字段

    private String fullLetter;//接口字段


    @Override
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }


    @Override
    public void setTitleType(boolean itemTitle) {
        this.titleItem = itemTitle;
    }

    @Override
    public String getFirstLetter() {
        if (StringUtil.isEmpty(firstLetter)) {
            if (StringUtil.isEmpty(getFullLetter())) {
                firstLetter = "#";
            } else {
                firstLetter = fullLetter.charAt(0) + "";
            }
        }
        return firstLetter;
    }

    @Override
    public boolean isTitleType() {
        return titleItem;
    }

    @Override
    public String getFullLetter() {
        if (fullLetter == null) {
            fullLetter = ChineseToSpell.getFullSpell(name);

            //Log.d("getFullLetter",""+fullLetter);
        }
        return fullLetter;
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

    @Override
    public String getSerachStr() {
        return getFullLetter() + phone;
    }


    /**
     * 仅作为APP界面设计使用
     *
     * @return
     */
    public String getTopTips() {
        return "";
    }

    /**
     * 仅作为APP界面设计使用
     *
     * @return
     */
    public String getBottomTips() {
        return "";
    }


    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setUserType2(int userType2) {
        this.userType2 = userType2;
    }

    public int getUserType2() {
        return userType2;
    }

    public long getLastContactTime() {
        if (lastContactTime == 0) {
            lastContactTime = System.currentTimeMillis() - random.nextInt(1000000000) - 1000000000;
        }
        return lastContactTime;
    }


    private long lastContactTime;




    public static String getUserTypeName(int userType) {
        String typeName = "意向客户";
        switch (userType) {
            case TYPE_USER_FORMAL:
                typeName = "正式客户";
                break;
            case TYPE_USER_INTENTION:
                typeName = "意向客户";
                break;
            case TYPE_USER_POTENTIAL:
                typeName = "潜在客户";
                break;
            case TYPE_USER_QUASI:
                typeName = "准客户";
                break;


        }
        return typeName;
    }


}
