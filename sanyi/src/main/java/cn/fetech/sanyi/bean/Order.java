package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qianjujun on 2015/7/29 0029 17:01.
 * qianjujun@163.com
 */
public class Order implements Serializable{

    private int id;

    private String orderNo;

    private int state;//状态

    private double totalPrice;

    private long time;

    private List<Goods> list;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }


    private String stateName;

    public String getStateName(){
        if(StringUtil.isEmpty(stateName)){
            switch (state){
                case 0:
                    stateName = "待审核";
                    break;
                case 1:
                    stateName = "审核通过";
                    break;
                default:
                    stateName = "待审核";
                    break;
            }
        }
        return stateName;
    }
}
