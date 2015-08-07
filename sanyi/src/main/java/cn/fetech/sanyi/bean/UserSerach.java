package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.StringUtil;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/7/7 0007 11:46.
 * qianjujun@163.com
 */
public class UserSerach implements Serializable {

    public static final int TYPE_TEXT = 1;

    public static final int TYPE_CONTACT = 2;

    public static final int TYPE_USERTYPE = 4;

    public static final int TYPE_CONTACT_AND_USERTYPE = 8;


    private final int searchType;

    private String editTextStr;

    private int userType;

    private long timeGaps;

    public String getEditTextStr() {
        return editTextStr;
    }

    public void setEditTextStr(String editTextStr) {
        this.editTextStr = editTextStr;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public long getTimeGaps() {
        return timeGaps;
    }

    public void setTimeGaps(long timeGaps) {
        this.timeGaps = timeGaps;
    }

    public int getSearchType() {
        return searchType;
    }


//    public UserSerach(int searchType) {
//
//        this.searchType = searchType;
//    }


    public UserSerach(String text) {
        this.searchType = TYPE_TEXT;
        this.editTextStr = text;
    }

    public UserSerach(long timeGaps) {
        this.searchType = TYPE_CONTACT;
        this.timeGaps = timeGaps;
    }

    public UserSerach(int userType) {
        this.searchType = TYPE_USERTYPE;
        this.userType = userType;
    }

    public UserSerach(long timeGaps, int userType) {
        this.searchType = TYPE_CONTACT_AND_USERTYPE;
        this.timeGaps = timeGaps;
        this.userType = userType;
    }


    public String getSearchDescription() {
        String searchDescription = "";
        switch (searchType) {
            case TYPE_USERTYPE:
                searchDescription = User.getUserTypeName(userType);
                break;
            case TYPE_TEXT:
                searchDescription = editTextStr;
                break;
            case TYPE_CONTACT:
                searchDescription = getTimeGapsDescription();
                break;
            case TYPE_CONTACT_AND_USERTYPE:
                searchDescription = getTimeGapsDescription()+" "+User.getUserTypeName(userType);
                break;
        }
        return searchDescription;
    }

    private String getTimeGapsDescription(){
        String searchDescription = "";
        switch (getWeek(timeGaps)) {
            case 1:
                searchDescription = "一周未联系";
                break;
            case 2:
                searchDescription = "两周未联系";
                break;
            default:
                searchDescription = "一个月未联系";
                break;
        }
        return searchDescription;
    }

    private int getWeek(long time) {
        long week = time / 1000 / 3600 / 24 / 7;
        return (int) week;
    }
}
