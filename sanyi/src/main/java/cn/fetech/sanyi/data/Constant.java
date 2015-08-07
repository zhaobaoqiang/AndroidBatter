package cn.fetech.sanyi.data;

/**
 * Created by qianjujun on 2015/7/7 0007 15:28.
 * qianjujun@163.com
 */
public interface Constant {

    int START_PAGE = 1;

    int PAGE_NUM = 10;



    /**
     * 资源搜索事广播Action
     */
    String BROADCAST_SEARCH = "cn.fetech.sanyi.search";


    String BROADCAST_CONVERT_TASK_SUCCESS = "cn.fetech.sanyi.convertTask";


    int SECLCT_USER_USERDETAIL_ORDER = 1;

    int SECLCT_USER_FOLLOW_ORDER = 2;

    /**
     * 参见AddTaskActivity 选择负责人
     */
    int SECLCT_USER_FUZEREN_ORDER = 3;




    /***************************************************************/
    /***********   赢丢单分析  AddYingdiuOrderActivity  *************/
    /***************************************************************/

    /**
     * 赢单+订单
     */
    int YING_ORDER = 1;

    /**
     * 丢单+订单
     */
    int DIU_ORDER = 2;

    /**
     * 赢单+合同
     */
    int YING_HETONG = 3;

    /**
     * 丢单+合同
     */
    int DIU_HETONG = 4;



    /***************************************************************/
    /***********   TASK type                           *************/
    /***************************************************************/

    int TASK_DAY = 1;

    int TASK_WEEK = 2;

    int TASK_ALL = 3;

    int TASK_DATE = 4;

}
