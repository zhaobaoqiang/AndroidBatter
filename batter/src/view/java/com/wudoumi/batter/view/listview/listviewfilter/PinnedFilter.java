package com.wudoumi.batter.view.listview.listviewfilter;

import android.widget.Filter;

import com.wudoumi.batter.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by qianjujun on 2015/7/2 0002 16:21.
 * qianjujun@163.com
 */
public abstract class PinnedFilter<T extends ISerach> extends Filter {
    public static final int TYPE_CONTAINS = 1;

    public static final int TYPE_STARTWITH = 2;

    protected List<T> iSeraches;


    private SerachWay serachWay;

    public PinnedFilter(List<T> iSeraches, int typeSerach) {
        this.iSeraches = iSeraches;

        switch (typeSerach){
            case TYPE_CONTAINS:
                serachWay = new ContainsSerachWay();
                break;
            case TYPE_STARTWITH:
                serachWay = new StartWithSerachWay();
                break;
            default:
                serachWay = new ContainsSerachWay();
                break;
        }
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String constraintStr = charSequence.toString().toLowerCase(Locale.getDefault());
        FilterResults result = new FilterResults();

        if (charSequence != null && charSequence.toString().length() > 0) {
            List<ISerach> filterItems = new ArrayList<ISerach>();

            synchronized (this) {
                for (ISerach item : iSeraches) {

                    if(serachWay.success(item.getSerachStr().toLowerCase(),constraintStr.toLowerCase())){
                        filterItems.add(item);
                    }
                }
                result.count = filterItems.size();
                result.values = filterItems;
            }
        } else {
            synchronized (this) {
                result.count = iSeraches.size();
                result.values = iSeraches;
            }
        }
        return result;
    }





    interface SerachWay{
        boolean success(String serachMaster,String serachStr);
    }

    class ContainsSerachWay implements SerachWay{

        @Override
        public boolean success(String serachMaster, String serachStr) {
            if(StringUtil.isEmpty(serachMaster)){
                return false;
            }
            if(serachMaster.contains(serachStr)){
                return true;
            }
            return false;
        }
    }

    class StartWithSerachWay implements SerachWay{

        @Override
        public boolean success(String serachMaster, String serachStr) {
            if(StringUtil.isEmpty(serachMaster)){
                return false;
            }
            if(serachMaster.startsWith(serachStr)){
                return true;
            }
            return false;
        }
    }
}
