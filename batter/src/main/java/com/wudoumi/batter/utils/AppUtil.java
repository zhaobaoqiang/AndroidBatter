package com.wudoumi.batter.utils;

import android.content.Context;

import com.wudoumi.batter.R;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class AppUtil {
    private Context context;
    private Map<String,Properties> map = new HashMap<>();

    private AppUtil(Context context){
        this.context = context;
    }

    private static AppUtil instance;

    public  static AppUtil getInstance(){
        if(instance==null){
            throw new RuntimeException("请在Appliction执行init");
        }
        return instance;
    }


    public static void init(Context context){
        instance = new AppUtil(context);
    }


    public String getPackegeName(){
        return context.getPackageName();
    }


    public String getAssentStringConfig(String configName,String key){
        Properties p = getAssentProperties(configName);
        if(p!=null){
            return p.getProperty(key);
        }
        return null;
    }


    private Properties getAssentProperties(String configName){
        if(!map.containsKey(configName)){
            Properties props = new Properties();
            try {
                //方法一：通过activity中的context攻取setting.properties的FileInputStream
                InputStream in = context.getAssets().open(configName);
                //方法二：通过class获取setting.properties的FileInputStream
                //InputStream in = PropertiesUtill.class.getResourceAsStream("/assets/  setting.properties "));
                props.load(in);

                map.put(configName,props);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                return null;
            }
        }

        if(map.containsKey(configName)){
            return map.get(configName);
        }else{
            return null;
        }

    }





}
