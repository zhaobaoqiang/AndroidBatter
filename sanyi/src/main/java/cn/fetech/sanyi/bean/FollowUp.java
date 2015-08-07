package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.DateUtil;
import com.wudoumi.batter.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/6 0006 16:31.
 * qianjujun@163.com
 */
public class FollowUp {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public static List<String> stateTypes = new ArrayList<>();


    private static Map<String,Integer> resMap = new HashMap<>();

    static {
        stateTypes.add("QQ");
        stateTypes.add("微信");
        stateTypes.add("电话");

        resMap.put("QQ",R.mipmap.icon_follow_huru);
        resMap.put("微信",R.mipmap.icon_follow_huchu);
        resMap.put("电话",R.mipmap.icon_follow_huru);
    }

    private int userId;

    private long time;

    private String content;

    private String stateType;

    private long nextFollowTime;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public long getNextFollowTime() {
        return nextFollowTime;
    }

    public void setNextFollowTime(long nextFollowTime) {
        this.nextFollowTime = nextFollowTime;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String timeStr;


    public String getTimeStr(){
        if(timeStr==null){
            timeStr = DateUtil.getStringTime(time,sdf);
        }
        return timeStr;
    }

    public int getStateIconRes(){
        return resMap.get(stateType);
    }


    /***********************************************************/
    /*****************以下为非界面展示字段************************/
    /***********************************************************/


    private long followTime;

    private boolean haveYuyue;

    private long canguanTime;

    private int jiedairenId;


    public long getFollowTime() {
        return followTime;
    }

    public void setFollowTime(long followTime) {
        this.followTime = followTime;
    }

    public boolean isHaveYuyue() {
        return haveYuyue;
    }

    public void setHaveYuyue(boolean haveYuyue) {
        this.haveYuyue = haveYuyue;
    }

    public long getCanguanTime() {
        return canguanTime;
    }

    public void setCanguanTime(long canguanTime) {
        this.canguanTime = canguanTime;
    }

    public int getJiedairenId() {
        return jiedairenId;
    }

    public void setJiedairenId(int jiedairenId) {
        this.jiedairenId = jiedairenId;
    }




    public String check(){
        if(StringUtil.isEmpty(content)){
            return "请填写内容";
        }
        if(StringUtil.isEmpty(stateType)){
            return "请选择联系工具";
        }

        if(isHaveYuyue()){
            if(jiedairenId==0){
                return "请选择接待人";
            }
        }
        return null;
    }
}
