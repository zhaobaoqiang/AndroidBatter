package cn.fetech.sanyi.bean;

import com.wudoumi.batter.utils.DateUtil;
import com.wudoumi.batter.utils.StringUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/6/25 0025 10:58.
 * qianjujun@163.com
 *
 *
 * 任务
 */
public class Task extends BaseBean{



    private long startTime;

    private long endTime;

    private String taskContent;

    private boolean importance;

    private boolean priority;

    private String form;

    private int fromUserId;


    private String state = "未完成";


    private String youxianji;

    public static List<String> importances = new ArrayList<>();


    static {
        importances.add("重要");
        importances.add("紧急");
        importances.add("重要且紧急");
        importances.add("普通");
    }


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public boolean isImportance() {
        return importance;
    }

    public void setImportance(boolean importance) {
        this.importance = importance;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    private String startTimeStr;

    private String endTimeStr;


    private int importanceRes;

    private int priorityRes;




    public String getStartTimeStr() {
        if (StringUtil.getInstance().isEmpty(startTimeStr)) {
            startTimeStr = DateUtil.getInstance().getStringTime(startTime, sdf);
        }
        return startTimeStr;
    }

    public String getEndTimeStr() {
        if (StringUtil.getInstance().isEmpty(endTimeStr)) {
            endTimeStr = DateUtil.getInstance().getStringTime(endTime, sdf);
        }
        return endTimeStr;
    }


    public Task() {

    }

    public Task(Message msg) {
        long[] times = DateUtil.getCurrentDayStartAndEnd();
        startTime = times[0];
        endTime = times[1];

        form = "转自消息";

        taskContent = msg.getTitle()+"  "+msg.getContent();

    }




    public String getYouxianji(){
        if(!StringUtil.isEmpty(youxianji)){
            return youxianji;
        }
        if(isImportance()&&!isPriority()){
            youxianji = "重要";
        }else if(!isImportance()&&isPriority()){
            youxianji = "紧急";
        }else if(isImportance()&&isPriority()){
            youxianji = "紧急且重要";
        }else{
            youxianji = "普通";
        }
        return youxianji;
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");





    //    public int getImportanceRes() {
//        if (importanceRes == 0) {
//            switch (importance) {
//                case 1:
//                    importanceRes = R.mipmap.test_importance;
//                    break;
//                case 2:
//                    importanceRes = R.mipmap.test_importance1;
//                    break;
//                default:
//                    importanceRes = R.mipmap.test_importance;
//                    break;
//            }
//        }
//        return importanceRes;
//    }
//
//    public int getPriorityRes() {
//        if (priorityRes == 0) {
//            switch (priority) {
//                case 1:
//                    priorityRes = R.mipmap.test_priority;
//                    break;
//                case 2:
//                    priorityRes = R.mipmap.test_priority1;
//                    break;
//                default:
//                    priorityRes = R.mipmap.test_priority;
//                    break;
//            }
//        }
//
//        return priorityRes;
//    }
}
