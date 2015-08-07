package cn.fetech.sanyi.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by qianjujun on 2015/7/14 0014 14:23.
 * qianjujun@163.com
 */
@DatabaseTable(tableName = "regions")
public class Regions {
    @DatabaseField(columnName = "id", id = true)
    private String id;

    @DatabaseField(columnName = "parent_id")
    private String parentID;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "alias")
    private String alias;

    @DatabaseField(columnName = "pinyin")
    private String pinyin;

    @DatabaseField(columnName = "abbr")
    private String abbr;

    @DatabaseField(columnName = "zip")
    private String zip;

    @DatabaseField(columnName = "level")
    private int level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
