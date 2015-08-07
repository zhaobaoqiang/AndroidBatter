package com.wudoumi.batter.utils;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2015/6/28.
 */
public class ViewUtil {

    public static View createNewView(String fullViewName,Context context){
        try {
            Class clazz = Class.forName(fullViewName);
            Constructor ct = clazz.getConstructor(Context.class);
            View view = (View) ct.newInstance(context);
            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }


    }
}
