package com.wudoumi.batter.utils;
/**
 * 通过单例模式来销毁所有activity
 */

import android.app.Activity;

import java.util.Stack;




/**
 * Activity 管理类
 * @author Administrator
 *
 */
public class ScreenManager {
	/**
	 * 单例
	 * @return
	 */
	public static ScreenManager getScreenManager() {
		if(instance==null){
			instance = new ScreenManager();
		}
		return instance;
	}
	/**
	 * 回收堆栈中指定activity
	 * @param activity
	 */
	public void popActivity(Activity activity){
		if(activity!=null){
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}
	/**
	 * 获取堆栈栈顶的activity
	 * @return
	 */
	public Activity currentActivity() {
		Activity activity = null;
		try {
			if(!activityStack.isEmpty()){
				activity = activityStack.pop();
			}
			return activity;
		} catch (Exception e) {
			return activity;
		} finally{
			activity = null;
		}

	}
	
	/**
	 * 获取栈顶的activity，只引用，不删除
	 * @return
	 */
	public Activity currrentActivityNoRemove(){
		Activity activity = null;
		try {
			if(!activityStack.isEmpty()){
				activity = activityStack.peek();
			}
			return activity;
		} catch (Exception e) {
			return activity;
		} 
	}
	/**
	 * 将activity压入堆栈
	 * @param activity
	 */
	public void pushActivity (Activity activity){
		if(activityStack==null){
			activityStack =  new Stack<Activity>();
		}
		activityStack.push(activity);
	}
	/**
	 * 回收堆栈中所有Activity
	 */
	public void popAllActivity() {
		Activity activity = null;
		try {
			while(!activityStack.isEmpty()){
				activity = currentActivity();
				popActivity(activity);
			}
		} finally {
			activity = null ;
		}
	
	}
	
	
	private ScreenManager(){
	}

	private static ScreenManager instance;
	private static Stack<Activity> activityStack;
	
	
	
	public boolean isExist(Class clazz){
		if(activityStack==null||activityStack.size()==0){
			return false;
		}
		for(Activity activity : activityStack){
			if(activity.getClass()==clazz){
				return true;
			}
		}
		return false;
	}

}
