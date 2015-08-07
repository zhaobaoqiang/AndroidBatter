package com.wudoumi.batter.view.listview.listviewfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qianjujun on 2015/6/29 0029 14:26.
 * qianjujun@163.com
 */
public class ListHashMap<K,V> extends HashMap<K,V>{

    private List<K> keyList;

    private List<V> valueList;

    public ListHashMap() {
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
    }


    @Override
    public V put(K key, V value) {
        if(keyList.contains(key)){
            valueList.remove(get(key));
            keyList.remove(key);
        }
        keyList.add(key);
        valueList.add(value);
        return super.put(key, value);
    }


    @Override
    public V remove(Object key) {
        if(keyList.contains(key)){
            valueList.remove(get(key));
            keyList.remove(key);
        }
        return super.remove(key);
    }


    @Override
    public void clear() {
        super.clear();
        keyList.clear();
        valueList.clear();
    }


    public int indexOf(V value){

        return valueList.indexOf(value);
    }


    public V get(int index){
        if(index<valueList.size()){
            return valueList.get(index);
        }
        return null;
    }


    public List<K> keyList(){
        return keyList;
    }
}
