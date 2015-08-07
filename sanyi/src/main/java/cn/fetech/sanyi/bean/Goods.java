package cn.fetech.sanyi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qianjujun on 2015/7/15 0015 13:08.
 * qianjujun@163.com
 */
public class Goods implements Serializable{

    public static int TYPE_QITA = 1;//其他
    public static int TYPE_BAJIAN = 2;//把件
    public static int TYPE_BAIJIAN = 3;//摆件
    public static int TYPE_ERSHI = 4;//耳饰
    public static int TYPE_GUAJIAN = 5;//挂件
    public static int TYPE_JIEZHI = 6;//戒指
    public static int TYPE_PAIZI = 7;//牌子
    public static int TYPE_XIANQIAN = 8;//镶嵌
    public static int TYPE_SHOULIAN = 9;//手链
    public static int TYPE_TAOJIAN = 10;//套件
    public static int TYPE_XIANGLIAN = 11;//项链
    public static int TYPE_YUANSHI = 12;//原石
    public static int TYPE_SHOUZHUO = 13;//手镯

    public static int TYPE_ALL = 0;//quanbu

    public static List<GoodsType> getTypes(){
        List<GoodsType> list = new ArrayList<>();
        list.add(new GoodsType(TYPE_ALL,"全部"));
        list.add(new GoodsType(TYPE_BAJIAN,"把件"));
        list.add(new GoodsType(TYPE_BAIJIAN,"摆件"));
        list.add(new GoodsType(TYPE_ERSHI,"耳饰"));
        list.add(new GoodsType(TYPE_GUAJIAN,"挂件"));
        list.add(new GoodsType(TYPE_JIEZHI,"戒指"));
        list.add(new GoodsType(TYPE_PAIZI,"牌子"));
        list.add(new GoodsType(TYPE_XIANQIAN,"镶嵌"));
        list.add(new GoodsType(TYPE_SHOULIAN,"手链"));
        list.add(new GoodsType(TYPE_TAOJIAN,"套件"));
        list.add(new GoodsType(TYPE_XIANGLIAN,"项链"));
        list.add(new GoodsType(TYPE_YUANSHI,"原石"));
        list.add(new GoodsType(TYPE_SHOUZHUO,"手镯"));
        list.add(new GoodsType(TYPE_QITA,"其他"));
        return list;
    }






    private String goodsNo;

    private String iconUrl;

    private String name;

    private double consignmentPrice;

    private double sellingPrice;


    private int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConsignmentPrice() {
        return consignmentPrice;
    }

    public void setConsignmentPrice(double consignmentPrice) {
        this.consignmentPrice = consignmentPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }



    public static class GoodsType{
        private int typeNo;
        private String typeName;

        public int getTypeNo() {
            return typeNo;
        }

        public void setTypeNo(int typeNo) {
            this.typeNo = typeNo;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public GoodsType(int typeNo, String typeName) {
            this.typeNo = typeNo;
            this.typeName = typeName;
        }
    }

    public static List<String> getGoodsType(){
        List<String> list = new ArrayList<>();
        list.add("翡翠");
        list.add("和田玉");
        list.add("收费项");
        list.add("其他");
        return list;
    }

    public static List<String> getQixing(){
        List<String> list = new ArrayList<>();
        list.add("把件");
        list.add("摆件");
        list.add("耳饰");
        list.add("挂件");
        list.add("戒指");
        list.add("牌子");
        list.add("镶嵌");
        list.add("手链");
        list.add("套件");
        list.add("项链");
        list.add("原石");
        list.add("手镯");
        list.add("其他");
        return list;
    }

    public static List<String> getTicai(){
        List<String> list = new ArrayList<>();
        list.add("仿古");
        list.add("翡翠手链");
        list.add("佛公");
        list.add("关公");
        list.add("观音");
        list.add("和田玉籽料无事牌");
        list.add("花鸟");
        list.add("吉祥平安");
        list.add("貔貅");
        list.add("人物");
        list.add("瑞兽");
        list.add("山水");
        list.add("其他");
        return list;
    }

    public static List<String> getZhongshui(){
        List<String> list = new ArrayList<>();
        list.add("玻璃种");
        list.add("冰种");
        list.add("冰糯");
        list.add("糯种");
        list.add("冰豆");
        list.add("豆种");
        list.add("其他");
        return list;
    }

    public static List<String> getColor(){
        List<String> list = new ArrayList<>();
        list.add("墨绿");
        list.add("带绿飘绿");
        list.add("红黄翡");
        list.add("紫翡");
        list.add("带兰飘兰");
        list.add("白色");
        list.add("墨翠");
        list.add("多彩");
        list.add("白玉");
        list.add("青白玉");
        list.add("碧玉");
        list.add("墨玉青花");
        list.add("糖玉");
        list.add("黄玉");
        list.add("浅绿");
        list.add("深绿");
        list.add("其他");
        return list;
    }

    public static List<String> getDanwei(){
        List<String> list = new ArrayList<>();
        list.add("副");
        list.add("件");
        return list;
    }



    public static List<String> getHave(){
        List<String> list = new ArrayList<>();
        list.add("有");
        list.add("无");
        return list;
    }

    public static List<String> getZhengshu(){
        List<String> list = new ArrayList<>();
        list.add("有");
        list.add("无");
        list.add("委托代做--已收费");
        list.add("委托代做--售后扣");
        list.add("邮寄");
        return list;
    }



}
