package cn.fetech.sanyi.bean;


import java.util.Random;

/**
 * Created by qianjujun on 2015/7/2 0002 12:59.
 * qianjujun@163.com
 */
public class ResourcesUser extends User {

    private long pilgrimageTime;

    public long getPilgrimageTime() {
        return pilgrimageTime;
    }

    public void setPilgrimageTime(long pilgrimageTime) {
        this.pilgrimageTime = pilgrimageTime;
    }

    @Override
    public String getTopTips() {
        return "距离下次回访";
    }

    @Override
    public String getBottomTips() {
        return new Random().nextInt(10) + "天";
    }


    @Override
    public int getUserType() {
        if(userType==0){
            userType = random.nextInt(3)+1;
        }
        return userType;
    }
}
