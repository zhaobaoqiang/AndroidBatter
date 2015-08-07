package cn.fetech.sanyi.data;

import cn.fetech.sanyi.bean.User;

/**
 * Created by qianjujun on 2015/7/30 0030 12:42.
 * qianjujun@163.com
 */
public class CommonData {

    private static CommonData commonData;

    public static CommonData getInstance(){
        if(commonData==null){
            synchronized (CommonData.class){
                if(commonData==null){
                    commonData = new CommonData();
                }
            }
        }
        return commonData;
    }


    private User loginUser;


    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
