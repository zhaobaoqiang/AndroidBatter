package com.wudoumi.batter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public abstract class BatterAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected Context context;
    protected LayoutInflater inflater;
    public BatterAdapter(List<T> list,Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public T getItem(int position) {
        return list==null?null:list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
