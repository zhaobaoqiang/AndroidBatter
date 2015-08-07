package com.wudoumi.battertest.bean;

import com.wudoumi.batter.view.listview.listviewfilter.IAutoConvertLetter;

/**
 * Created by qianjujun on 2015/7/2 0002 12:59.
 * qianjujun@163.com
 */
public class ResourcesUser implements IAutoConvertLetter{
    private int _id;

    private int id;

    private String name;

    private String phone;

    private String faceUrl;

    private boolean titleItem;


    private String firstLetter;




    @Override
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Override
    public void setTitleType(boolean itemTitle) {
        this.titleItem = itemTitle;
    }

    @Override
    public String getFirstLetter() {
        return null;
    }

    @Override
    public boolean isTitleType() {
        return titleItem;
    }

    @Override
    public String getFullLetter() {
        return null;
    }
}
