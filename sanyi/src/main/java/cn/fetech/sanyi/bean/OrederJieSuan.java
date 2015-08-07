package cn.fetech.sanyi.bean;

import java.io.Serializable;

/**
 * Created by qianjujun on 2015/7/30 0030 10:52.
 * qianjujun@163.com
 */
public class OrederJieSuan extends JieSuan{
    private int orderId;

    private String orderNo;


    private double dingjin;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public double getDingjin() {
        return dingjin;
    }

    public void setDingjin(double dingjin) {
        this.dingjin = dingjin;
    }
}
