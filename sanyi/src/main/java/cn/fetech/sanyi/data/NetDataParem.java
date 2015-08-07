package cn.fetech.sanyi.data;

import com.wudoumi.batter.net.HttpRequetParem;
import com.wudoumi.batter.volley.toolbox.RequestParem;
import com.wudoumi.batter.volley.toolbox.RequestType;

/**
 * Created by qianjujun on 2015/8/4 0004 15:06.
 * qianjujun@163.com
 */
public class NetDataParem {

    public static RequestParem getBaseUserParem(int userID,String url){
        RequestParem requestParem = new HttpRequetParem(RequestType.POST,url);
        requestParem.put("userId",userID+"");
        return requestParem;
    }

    
    public static RequestParem getUserParem(int userID,int type){
        RequestParem requestParem = new HttpRequetParem(RequestType.POST,NetDataUtil.GET_USER);
        requestParem.put("userId",userID+"");
        requestParem.put("type",type+"");
        return requestParem;
    }

    public static RequestParem getTaskParem(int userID,int type){
        RequestParem requestParem = getBaseUserParem(1,NetDataUtil.GET_TASK);
        requestParem.put("type",type+"");
        if(type==Constant.TASK_ALL||type==Constant.TASK_DATE){
            requestParem.put("pageNum",Constant.PAGE_NUM+"");
        }
        return requestParem;
    }

    public static RequestParem getTaskParem(int userID,long time){
        RequestParem requestParem = getBaseUserParem(1,NetDataUtil.GET_TASK);
        requestParem.put("time",time+"");
        return requestParem;
    }
}
