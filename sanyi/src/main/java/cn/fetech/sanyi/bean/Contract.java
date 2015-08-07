package cn.fetech.sanyi.bean;

import java.util.List;

/**
 * Created by qianjujun on 2015/7/30 0030 14:50.
 * qianjujun@163.com
 *
 *
 * 合同
 */
public class Contract {

    private int id;

    private String contractNo;

    private String state;

    private List<Goods> list;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}
