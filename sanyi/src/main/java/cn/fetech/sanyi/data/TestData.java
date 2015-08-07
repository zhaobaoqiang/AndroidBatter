package cn.fetech.sanyi.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import cn.fetech.sanyi.bean.Contract;
import cn.fetech.sanyi.bean.ContractJieSuan;
import cn.fetech.sanyi.bean.CustomUser;
import cn.fetech.sanyi.bean.FollowUp;
import cn.fetech.sanyi.bean.Fuwu;
import cn.fetech.sanyi.bean.Goods;
import cn.fetech.sanyi.bean.OrederJieSuan;
import cn.fetech.sanyi.bean.Message;
import cn.fetech.sanyi.bean.Order;
import cn.fetech.sanyi.bean.OrderAnalysis;
import cn.fetech.sanyi.bean.PublicResourcesUser;
import cn.fetech.sanyi.bean.Qingjiadan;
import cn.fetech.sanyi.bean.ResourcesContacts;
import cn.fetech.sanyi.bean.ResourcesUser;
import cn.fetech.sanyi.bean.SupplierUser;
import cn.fetech.sanyi.bean.Task;
import cn.fetech.sanyi.bean.TxlParent;
import cn.fetech.sanyi.bean.TxlUser;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.bean.Zhanting;
import cn.fetech.sanyi.fragment.MainUserChildFragment;

/**
 * Created by qianjujun on 2015/6/25 0025 12:48.
 * qianjujun@163.com
 */
public class TestData {

    private static Random random = new Random();

    private static String[] taskContents = new String[]{"Google 免费的在线翻译服务可即时翻译文本和网页。 该翻译器支持: 中文(简体), 阿尔巴尼亚语, 阿拉伯语, 阿塞拜疆语, 爱尔兰语, 爱沙尼亚语, 巴斯克语, 白俄罗斯语"
    ,"2001年秋，北京外国语大学推出英语翻译资格证书考试。《英汉翻译简明教程》就是应邀专为配合这一考试而编写的。"
    ,"任务是日常生活中，通常指交派的工作，担负的职责、责任。在众多游戏中，有目的的指引玩家进行游戏活动，并给予玩家一定奖励的手段，即为游戏任务。"
    ,"任务的类型多种多样，一般可以分为以下几种："};

    private static String[] taskFroms = new String[]{"资源管理","王主任","力经理"};










    public static List<Task> createTestTasks(){
        List<Task> tasks = new ArrayList<>();
        for(int i = 0;i<15;i++){
            tasks.add(createTask());
        }
        return tasks;
    }



    private static Task createTask(){
        int timeChaStart = random.nextInt(1000000)+100000;
        int timeChaEnd = random.nextInt(1000000);
        Task task = new Task();
        task.setStartTime(System.currentTimeMillis()-timeChaStart-timeChaEnd);
        task.setEndTime(System.currentTimeMillis()-timeChaStart);
        task.setImportance(random.nextBoolean());
        task.setPriority(random.nextBoolean());
        task.setTaskContent(taskContents[random.nextInt(4)]);
        task.setForm(taskFroms[random.nextInt(3)]);
        return task;
    }


    private static long createRadomTime(){
        return System.currentTimeMillis()-random.nextInt(1000000);
    }

    private static String createRadomContent(){
        return taskContents[random.nextInt(4)];
    }








    public static List<ResourcesUser> createResourcesUser(int type){
        List<ResourcesUser> list = new ArrayList<>();
        ResourcesUser resourcesUser = null;
        int max = type==0?20:80;
        String name = "";
        for(int i = 0;i<max;i++){
            resourcesUser = new ResourcesUser();
            name = Data.FIRSTNAME.charAt(random.nextInt(Data.FIRSTNAME.length()))+Data.fullName[random.nextInt(Data.fullName.length)];
            name = name.replaceAll("\\s","");
            name = name.length()>3?name.substring(0,3):name;

            resourcesUser.setName(name);

            resourcesUser.setPhone(createPhone());

            list.add(resourcesUser);
        }

        return list;
    }

    public static List<User> createUser(int type){
        List<User> list = new ArrayList<>();
        User user = null;
        int max = type==0?20:80;
        String name = "";
        for(int i = 0;i<max;i++){
            user = getUser(type);
            name = Data.FIRSTNAME.charAt(random.nextInt(Data.FIRSTNAME.length()))+Data.fullName[random.nextInt(Data.fullName.length)];
            name = name.replaceAll("\\s","");
            name = name.length()>3?name.substring(0,3):name;

            user.setName(name);

            user.setPhone(createPhone());

            list.add(user);
        }

        return list;
    }


    public static String createName(){
       String  name = Data.FIRSTNAME.charAt(random.nextInt(Data.FIRSTNAME.length()))+Data.fullName[random.nextInt(Data.fullName.length)];
        name = name.replaceAll("\\s","");
        name = name.length()>3?name.substring(0,3):name;
        return name;
    }
    private static User getUser(int type){
        User user = null;
        switch (type){
            case MainUserChildFragment.TYPE_PUBLIC:
                user = new PublicResourcesUser();
                user.setUserType2(User.TYPE2_PUBLIC);
                break;
            case MainUserChildFragment.TYPE_MINE:
                user = new ResourcesUser();
                user.setUserType2(User.TYPE2_PRIVATE);
                break;
            case MainUserChildFragment.TYPE_CUSTOM:
                user = new CustomUser();
                user.setUserType2(User.TYPE2_CUSTOM);
                break;
            case MainUserChildFragment.TYPE_SUPPLIER:
                user = new SupplierUser();
                user.setUserType2(User.TYPE2_SUPPLIER);
                break;
        }

        return user;
    }


    private static String createPhone(){
        String phone = "13";
        for(int i = 0;i<9;i++){
            phone += random.nextInt(10);
        }

        return phone;

    }



    public static List<FollowUp> createFollowUps(){
        List<FollowUp> followUps = new ArrayList<>();
        FollowUp followUp = null;
        long currentTime = System.currentTimeMillis();
        for(int i = 0;i<50;i++){
            followUp = new FollowUp();
            followUp.setContent(taskContents[random.nextInt(taskContents.length)]);
            followUp.setStateType(FollowUp.stateTypes.get(random.nextInt(3)));
            followUp.setTime(currentTime-random.nextInt(1000000)-1000000);
            followUp.setNextFollowTime(currentTime + random.nextInt(1000000) + 1000000);
            followUps.add(followUp);
        }

        Collections.sort(followUps, new Comparator<FollowUp>() {
            @Override
            public int compare(FollowUp followUp, FollowUp t1) {
                return (int)(followUp.getTime()-t1.getTime());
            }
        });
        return followUps;
    }



    public static List<ResourcesContacts> createResourcesUsers(){
        List<ResourcesContacts> list = new ArrayList<>();
        ResourcesContacts user = null;
        String name = "";
        for(int i = 0;i<5;i++){
            user = new ResourcesContacts();
            name = Data.FIRSTNAME.charAt(random.nextInt(Data.FIRSTNAME.length()))+Data.fullName[random.nextInt(Data.fullName.length)];
            name = name.replaceAll("\\s","");
            name = name.length()>3?name.substring(0,3):name;

            user.setName(name);

            user.setPhone(createPhone());

            list.add(user);
        }
        return list;
    }



    public static List<Goods> createGoods(int type){
        List<Goods> goodses = new ArrayList<>();
        Goods goods = null;
        for(int i = 0;i<10;i++){
            goods = new Goods();
            goods.setGoodsNo("150608-2");
            goods.setName("测试货品");
            goods.setType(type);
            goodses.add(goods);
        }
        return goodses;
    }


    private static Goods createGoods(){
        Goods goods = new Goods();
        goods.setGoodsNo("150608-"+random.nextInt(100));
        goods.setName("测试货品");
        return goods;
    }



    public static List<TxlParent> getTxlParents(){
        List<TxlParent> txlParents = new ArrayList<>();
        TxlParent txlParent = null;
        TxlParent child = null;
        for(int i =1;i<=5;i++){
            txlParent = new TxlParent();
            txlParent.setId(i);
            txlParent.setParentId(0);
            txlParent.setName("测试"+i);
            txlParents.add(txlParent);
            for(int j = 1;j<10;j++){
                child = new TxlParent();
                child.setParentId(i);
                child.setName("测试子目录"+j);
                txlParents.add(child);
            }
        }

        return txlParents;
    }

    public static List<TxlUser> createTxlUsers(){
        List<TxlUser> txlUsers = new ArrayList<>();
        TxlUser txlUser = null;
        for(int i = 0;i<30;i++){
            txlUser = new TxlUser(random.nextInt(100)%2==0?"组长":"销售员");
            txlUser.setName(createName());
            txlUser.setPhone(createPhone());
            txlUsers.add(txlUser);
        }
        return txlUsers;
    }


    public static List<Message> getMessages(){
        List<Message> list = new ArrayList<>();
        Message message = null;
        for(int i = 0;i<10;i++){
            message = new Message();

            message.setTime(createRadomTime());
            message.setTitle("消息"+random.nextInt(1000));
            message.setContent(createRadomContent());

            list.add(message);
        }

        return list;
    }

    private static String[] results = new String[]{"丢单","赢单"};
    private static String createResult(){
        return results[random.nextInt(2)];
    }

    public static List<Zhanting> createZhangTing(){
        List<Zhanting> zhantings = new ArrayList<>();
        Zhanting zhanting = null;
        for(int i = 0;i<10;i++){
            zhanting = new Zhanting();
            zhanting.setContent(createRadomContent());
            zhanting.setJiedaiTime(createRadomTime());
            zhanting.setJiedaiUser(createName());
            zhanting.setResult(createResult());
            zhantings.add(zhanting);
        }

        return zhantings;
    }

    private static String[] orderContents = new String[]{"货品很好","铺垫到位，对平台信任","客户对货品不满意","测试分析测试分析测试分析1","测试分析2"};
    private static List<String> createOrderAnalysisContent(){
        List<String> list = new ArrayList<>();
        int max = random.nextInt(5)+2;
        for(int i = 0;i<max;i++){
            list.add(orderContents[random.nextInt(orderContents.length)]);
        }

        return list;
    }

    private static String[] fenxi = new String[]{"丢单分析","赢单分析"};

    public static List<OrderAnalysis> createOrderAnalysis(){
        List<OrderAnalysis> orderAnalysises = new ArrayList<>();
        OrderAnalysis orderAnalysis = null;
        for(int i = 0;i<10;i++){
            orderAnalysis = new OrderAnalysis();
            orderAnalysis.setContents(createOrderAnalysisContent());
            orderAnalysis.setTime(createRadomTime());
            orderAnalysis.setType(random.nextBoolean()?fenxi[0]:fenxi[1]);
            orderAnalysises.add(orderAnalysis);
        }

        return orderAnalysises;
    }


    public static List<Order> createOrders(){
        List<Order> orders = new ArrayList<>();
        Order order = null;

        List<Goods> goodses;
        for(int i = 0;i<10;i++){
            order = new Order();
            goodses = new ArrayList<>();
            int max = random.nextInt(5)+1;
            for(int j = 0;j<max;j++){
                goodses.add(createGoods());
            }
            order.setList(goodses);

            order.setTime(createRadomTime());
            order.setOrderNo(createOrderNo());
            order.setState(random.nextInt(3));

            orders.add(order);
        }

        return orders;
    }


    private static String createOrderNo(){
        return "2015"+(random.nextInt(12)+1)+""+(random.nextInt(30)+1)+"-"+random.nextInt(100);
    }




    public static List<OrederJieSuan> createJisuan(){
        List<OrederJieSuan> list = new ArrayList<>();
        OrederJieSuan orederJieSuan = null;
        for(int i = 0;i<10;i++){
            orederJieSuan = new OrederJieSuan();
            orederJieSuan.setOrderNo(createOrderNo());
            orederJieSuan.setTime(createRadomTime());
            orederJieSuan.setWeikuan(random.nextInt(1000));
            orederJieSuan.setYingshou(random.nextInt(1000));
            orederJieSuan.setYishou(random.nextInt(1000));
            list.add(orederJieSuan);
        }

        return list;
    }

    public static List<ContractJieSuan> createHetongJisuan(){
        List<ContractJieSuan> list = new ArrayList<>();
        ContractJieSuan orederJieSuan = null;
        for(int i = 0;i<10;i++){
            orederJieSuan = new ContractJieSuan();
            orederJieSuan.setGoodsNo(createOrderNo());
            orederJieSuan.setTime(createRadomTime());
            orederJieSuan.setWeikuan(random.nextInt(1000));
            orederJieSuan.setYingshou(random.nextInt(1000));
            orederJieSuan.setYishou(random.nextInt(1000));
            orederJieSuan.setFujiafei(createFujiafei());
            list.add(orederJieSuan);
        }

        return list;
    }

    private static List<String> createFujiafei(){
        List<String> list = new ArrayList<>();
        list.add("展览费  300");
        list.add("证书费  300");
        return list;
    }


    private static final String[] fuwuStates = new String[]{"已申请变更","已批准"};
    private static final String[] fuwuTitle = new String[]{"退货","换货"};

    public static List<Fuwu> createfuwu(){
        List<Fuwu> list = new ArrayList<>();
        Fuwu fuwu = null;
        for(int i = 0;i<10;i++){
            fuwu = new Fuwu();
            fuwu.setTime(createRadomTime());
            fuwu.setState(fuwuStates[random.nextInt(2)]);
            fuwu.setTitle(fuwuTitle[random.nextInt(2)]);
            fuwu.setContent(taskContents[random.nextInt(4)]);
            list.add(fuwu);
        }

        return list;
    }

    private static String[] hetong_fuwustates = new String[]{"已申请","已批准","不批准"};

    private static String[] hetong_fuwutitles = new String[]{"售前改价","售后改价","取货"};

    public static List<Fuwu> createfuwu(int type){
        if(type==Fuwu.TYPE_CUSTOM){
            return createfuwu();
        }
        List<Fuwu> list = new ArrayList<>();
        Fuwu fuwu = null;
        for(int i = 0;i<10;i++){
            fuwu = new Fuwu();
            fuwu.setType(type);
            fuwu.setTime(createRadomTime());
            fuwu.setState(hetong_fuwustates[random.nextInt(3)]);
            fuwu.setTitle(hetong_fuwutitles[random.nextInt(3)]);
            fuwu.setContent(taskContents[random.nextInt(4)]);
            list.add(fuwu);
        }
        return list;
    }



    private static String[] hetongStates = new String[]{"待审核","审核通过","审核不通过"};

    public static List<Contract> createContract(){
        List<Contract> list = new ArrayList<>();
        Contract contract = null;
        List<Goods> goodses = null;
        int maxGoods = random.nextInt(5)+1;
        for(int i = 0;i<10;i++){
            contract = new Contract();
            contract.setContractNo(createOrderNo());
            contract.setState(hetongStates[random.nextInt(3)]);
            goodses = new ArrayList<>();
            for(int j = 0;j<maxGoods;j++){
                goodses.add(createGoods());
            }
            contract.setList(goodses);
            list.add(contract);
        }

        return list;
    }

    private static String[] qjdStates = new String[]{"已申请","已批准","不批准"};
    private static String[] qjdTitle = new String[]{"事假","病假","年假"};
    public static List<Qingjiadan> createQingjiadans(){
        List<Qingjiadan> qingjiadans = new ArrayList<>();
        Qingjiadan qingjiadan;
        for(int i = 0;i<10;i++){
            qingjiadan = new Qingjiadan();
            qingjiadan.setState(qjdStates[random.nextInt(3)]);
            qingjiadan.setContent(createRadomContent());
            qingjiadan.setCreateTime(createRadomTime());
            qingjiadan.setReciverName(createName());
            qingjiadan.setTitle(qjdTitle[random.nextInt(3)]);
            qingjiadans.add(qingjiadan);
        }
        return qingjiadans;
    }

}
