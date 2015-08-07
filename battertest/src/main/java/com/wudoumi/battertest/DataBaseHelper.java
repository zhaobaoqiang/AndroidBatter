package com.wudoumi.battertest;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.wudoumi.batter.db.BatterDatabaseHelper;
import com.wudoumi.battertest.bean.User;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class DataBaseHelper extends BatterDatabaseHelper{
    private static final String DATABASE_NAME = "test_ormilte";
    private static final int DATABASE_VERSION = 1;
    private static final Class<?>[] clazzes = new Class[]{User.class};

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, DATABASE_VERSION, clazzes);
    }

    private static DataBaseHelper instance;

    public static void init(Context applicationContext){
        if(instance == null){
            synchronized (DataBaseHelper.class){
                if(instance==null){
                    instance = OpenHelperManager.getHelper(applicationContext,DataBaseHelper.class);
                }
            }
        }
    }

    public static DataBaseHelper getHelper(){
        if(instance==null){
            throw new RuntimeException("请调用init");
        }
        return instance;
    }


    public static void relaseHelper(){
        if(instance!=null){
            OpenHelperManager.releaseHelper();
            instance = null;
        }
    }

}
