package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;

public class MyClass {

    private String str = "aaaa";


    private void change(String str){
        str = "bbbb";
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.change(myClass.str);
        System.out.println(myClass.str);


       // String str = "aaa:astr";

       //String[] strs = str.split(";");

       // System.out.println(Arrays.toString(strs)+strs.length);



        //getRealFileNameFromUrl("http://dlsw.baidu.com/sw-search-sp/soft/3a/12350/QQ_V7.4.15197.0_setup.1436951158.exe");

//        String str = "\r\naaaa      ";
//
//        //test(str);
//        System.out.println(str.length());
//
//       // str = str.replace("\\s","");
//
//
//        str = str.replaceAll("\\s","");
//        System.out.println(str.length());
//        System.out.println(str);


//        System.out.print("fffff");
//
//        try {
//            Class clazz = Class.forName("");
//            Constructor ct = clazz.getConstructor(Context.class);
//            ct.newInstance(null);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        //Android andoird = Class.forName("").newInstance();
    }


    public static void test(String str) {
        str = new String();
    }


    private static Map<String, String> getMapForStr(String str) {
        Map<String, String> map = new HashMap<>();
        //String[] strs = str.split()
        return map;
    }

    public static String getRealFileNameFromUrl(String url){
        String name = null;
        try {


            URL mUrl = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(5 * 1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept","image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent","");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200){
                for (int i = 0;; i++) {
                    String mine = mHttpURLConnection.getHeaderField(i);
                    if (mine == null){
                        break;
                    }
                    if ("content-disposition".equals(mHttpURLConnection.getHeaderFieldKey(i).toLowerCase())) {
                        Matcher m = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
                        if (m.find())
                            return m.group(1).replace("\"", "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


}
