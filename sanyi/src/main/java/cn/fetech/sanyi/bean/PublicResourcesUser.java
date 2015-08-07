package cn.fetech.sanyi.bean;



/**
 * Created by qianjujun on 2015/8/5 0005 11:10.
 * qianjujun@163.com
 */
public class PublicResourcesUser extends User{
    @Override
    public String getTopTips() {
        return "跟进人次："+random.nextInt(10)+"人";
    }

    @Override
    public String getBottomTips() {
        return "跟进次数："+random.nextInt(100)+"次";
    }
}
