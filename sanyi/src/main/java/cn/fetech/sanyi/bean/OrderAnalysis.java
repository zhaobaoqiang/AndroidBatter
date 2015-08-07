package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qianjujun on 2015/7/29 0029 15:54.
 * qianjujun@163.com
 */
public class OrderAnalysis implements Serializable{
    private int id;

    private String type;//赢单，丢单

    private List<String> contents;


    private String content;

    private long time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public String getContent() {
        if(StringUtil.isEmpty(content)&&contents!=null){
            content = "";
            for(int i = 0;i<contents.size();i++){
                if(i==contents.size()-1){
                    content += (i+1)+"."+contents.get(i);
                }else{
                    content += (i+1)+"."+contents.get(i)+"\r\n";
                }
            }

        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
