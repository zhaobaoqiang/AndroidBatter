package com.wudoumi.batter.volley.toolbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class RequestParem extends HashMap<String, String> {
    private List<String> keys = new ArrayList<String>();

    public abstract int getRequestType();

    public abstract String getUrl();

    public abstract String getNameSpace();

    public abstract String getMethod();


    public abstract void setMapParem(Map<String,String> map);


    public boolean useSoapAction(){
        return false;
    }


    @Override
    public String put(String key, String value) {
        // TODO Auto-generated method stub
        if (!containsKey(key)) {
            keys.add(key);
        }
        return super.put(key, value);
    }


    @Override
    public String remove(Object key) {
        // TODO Auto-generated method stub
        if (containsKey(key)) {
            keys.remove(key);
        }
        return super.remove(key);
    }

    public List<String> keyList() {
        return keys;
    }


}
