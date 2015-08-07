package cn.fetech.sanyi.bean;

import android.widget.Filter;

import com.google.gson.Gson;
import com.wudoumi.batter.utils.StringUtil;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianjujun on 2015/7/7 0007 11:48.
 * qianjujun@163.com
 */
public class UserSerachFilter extends PinnedFilter {

    private static Gson gson = new Gson();

    private final List<User> users;

    private final PublishResults publishResults;


    private int currentSearchType = 0;

    private String serachText = "";

    public UserSerachFilter(List<User> iSeraches, PublishResults publishResults){
        super(iSeraches,TYPE_CONTAINS);
        this.publishResults = publishResults;
        this.users = iSeraches;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        UserSerach userSerach = gson.fromJson(charSequence.toString(), UserSerach.class);
        currentSearchType = userSerach.getSearchType();
        serachText = userSerach.getEditTextStr();
        switch (currentSearchType) {
            case UserSerach.TYPE_TEXT:
                return super.performFiltering(serachText);
            case UserSerach.TYPE_CONTACT:
                return performContact(userSerach.getTimeGaps());
            case UserSerach.TYPE_USERTYPE:
                return performUserType(userSerach.getUserType());
            case UserSerach.TYPE_CONTACT_AND_USERTYPE:
                return performContactAndUserType(userSerach.getTimeGaps(),userSerach.getUserType());

        }
        return super.performFiltering(charSequence);
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        List<User> list = (List<User>) filterResults.values;
        publishResults.handler(currentSearchType==UserSerach.TYPE_TEXT&& StringUtil.isEmpty(serachText),list);
    }


    private FilterResults performContact(long timegaps){
        FilterResults result = new FilterResults();
        List<User> temp = new ArrayList<>();
        synchronized (this){
            for(User user : users){
                if(handlerContact(user.getLastContactTime(),timegaps)){
                    temp.add(user);
                }
            }
            result.count = temp.size();
            result.values = temp;
        }
        return result;
    }

    private FilterResults performUserType(int userType){
        FilterResults result = new FilterResults();
        List<User> temp = new ArrayList<>();
        synchronized (this){
            for(User user : users){
                if(handlerUserType(user.getUserType(),userType)){
                    temp.add(user);
                }
            }
            result.count = temp.size();
            result.values = temp;
        }
        return result;
    }

    private FilterResults performContactAndUserType(long timegaps,int userType){
        FilterResults result = new FilterResults();
        List<User> temp = new ArrayList<>();
        synchronized (this){
            for(User user : users){
                if(handlerUserType(user.getUserType(),userType)&&handlerContact(user.getLastContactTime(),timegaps)){
                    temp.add(user);
                }
            }
            result.count = temp.size();
            result.values = temp;
        }
        return result;
    }


    private boolean handlerContact(long lastTime,long timegaps){
        return System.currentTimeMillis()-lastTime<timegaps;
    }

    private boolean handlerUserType(int userTypeOwn,int userType){
        return userTypeOwn==userType;
    }



    public static interface PublishResults{
        void handler(boolean showBar,List<User> users);
    }
}
