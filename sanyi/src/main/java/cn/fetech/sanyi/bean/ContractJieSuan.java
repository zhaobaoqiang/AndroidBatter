package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qianjujun on 2015/7/30 0030 15:54.
 * qianjujun@163.com
 */
public class ContractJieSuan extends JieSuan{


    private int goodsId;

    private String goodsNo;

    private List<String> fujiafei;



    public List<String> getFujiafei() {
        return fujiafei;
    }

    public void setFujiafei(List<String> fujiafei) {
        this.fujiafei = fujiafei;
    }


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    private String fujiafeiStr;
    public String getFujiafeiStr(){
        if(!StringUtil.isEmpty(fujiafeiStr)){
            return fujiafeiStr;
        }

        if(fujiafei==null||fujiafei.size()==0){
            return "无";
        }
        fujiafeiStr = "";

        for(int i = 0;i<fujiafei.size();i++){
            if(i==fujiafei.size()-1){
                fujiafeiStr += fujiafei.get(i);
            }else{
                fujiafeiStr += fujiafei.get(i)+"\r\n";
            }
        }

        return fujiafeiStr;
    }
}
