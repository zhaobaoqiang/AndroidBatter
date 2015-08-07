package cn.fetech.sanyi.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.net.NetSuccessHandler;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.listview.listviewfilter.ConvertData;
import com.wudoumi.batter.view.listview.listviewfilter.ListHashMap;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedAdapter;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedFilter;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedHeaderListView;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.SanyiApp;
import cn.fetech.sanyi.activity.CoustomUserActivity;
import cn.fetech.sanyi.activity.CreateHetongActivity;
import cn.fetech.sanyi.activity.CreateOrderActivity;
import cn.fetech.sanyi.activity.CustomTuihuanActivity;
import cn.fetech.sanyi.activity.QuhuoActivity;
import cn.fetech.sanyi.activity.ResourcesUserActivity;
import cn.fetech.sanyi.activity.SupplierGaijiaActivity;
import cn.fetech.sanyi.activity.SupplierUserActivity;
import cn.fetech.sanyi.adapter.UserAdapter;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.bean.UserSerach;
import cn.fetech.sanyi.bean.UserSerachFilter;
import cn.fetech.sanyi.data.NetDataParem;
import cn.fetech.sanyi.data.TestData;

/**
 * Created by qianjujun on 2015/7/2 0002 12:41.
 * qianjujun@163.com
 */
public class MainUserChildFragment extends BatterLoadingFragment {

    private Gson gson = new Gson();


    public static final int TYPE_MINE = 0;

    public static final int TYPE_PUBLIC = 1;


    public static final int TYPE_CUSTOM = 2;

    public static final int TYPE_SUPPLIER = 3;

    private int type = TYPE_MINE;


    List<User> itemsAll;

    List<User> adapterItems = new ArrayList<>();

    ListHashMap<String, Integer> map = new ListHashMap<>();

    UserAdapter resourcesUserAdapter;


    private boolean isHandlerSuccess = false;

    private UserSerachFilter userSerachFilter;


    @ViewInject(R.id.list_view)
    PinnedHeaderListView pinnedHeaderListView;

    @ViewInject(R.id.root)
    private View root;

    private PopupWindow userPopupWindow;


    private static List<String> privateZiyuan = new ArrayList<>();
    private static List<String> publicZiyuan = new ArrayList<>();

    private static List<String> custom = new ArrayList<>();
    private static List<String> gongyingshang = new ArrayList<>();


    private List<String> popList = new ArrayList<>();
    private ArrayAdapter<String> popAdapter;



    private User longClickUser = null;

    static {
        privateZiyuan.add("新合同");
        privateZiyuan.add("新订单");
        privateZiyuan.add("跟进");

        publicZiyuan.add("封存");
        publicZiyuan.add("认领");
        publicZiyuan.add("分配");


        custom.add("写回访");
        custom.add("新订单");
        custom.add("退换货");
        custom.add("转供应商");


        gongyingshang.add("写回访");
        gongyingshang.add("新合同");
        gongyingshang.add("取货");
        gongyingshang.add("价格变更");
        gongyingshang.add("转客户");
    }


    public static MainUserChildFragment getInstance(int type) {
        MainUserChildFragment fragment = new MainUserChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MainUserChildFragment getInstance(int type, int parentType) {
        MainUserChildFragment fragment = new MainUserChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type", TYPE_MINE);


    }


    private void initPop() {


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_user_menu, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userPopupWindow!=null){
                    userPopupWindow.dismiss();
                }
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.listview);

        popAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_pop_user, R.id.text, popList);
        listView.setAdapter(popAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(userPopupWindow!=null){
                    userPopupWindow.dismiss();
                }

                if(longClickUser==null){
                    ToastUtil.showToast(getActivity(),"该客户信息不全");
                    return;
                }

                if("新订单".equals(popList.get(position))){
                    Intent intent = new Intent(getActivity(), CreateOrderActivity.class);
                    intent.putExtra("User", longClickUser);
                    getActivity().startActivity(intent);
                }else if("新合同".equals(popList.get(position))){
                    Intent intent = new Intent(getActivity(), CreateHetongActivity.class);
                    intent.putExtra("User", longClickUser);
                    getActivity().startActivity(intent);
                }else if("跟进".equals(popList.get(position))||"写回访".equals(popList.get(position))){
                    Intent intent = new Intent(getActivity(), ResourcesUserActivity.class);
                    intent.putExtra("User", longClickUser);
                    getActivity().startActivity(intent);
                }else if("退换货".equals(popList.get(position))){
                    Intent intent = new Intent(getActivity(), CustomTuihuanActivity.class);
                    intent.putExtra("User", longClickUser);
                    getActivity().startActivity(intent);
                }else if("取货".equals(popList.get(position))){
                    Intent intent = new Intent(getActivity(), QuhuoActivity.class);
                    intent.putExtra("User", longClickUser);
                    getActivity().startActivity(intent);
                }else if("价格变更".equals(popList.get(position))){
                    Intent intent = new Intent(getActivity(), SupplierGaijiaActivity.class);
                    intent.putExtra("User", longClickUser);
                    getActivity().startActivity(intent);
                }
            }
        });
        userPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        userPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        userPopupWindow.setFocusable(true);
        userPopupWindow.setOutsideTouchable(true);
    }

    private void showUserPop(View view) {
        popList.clear();
        switch (type) {
            case TYPE_MINE:
                popList.addAll(privateZiyuan);
                break;
            case TYPE_PUBLIC:
                popList.addAll(publicZiyuan);
                break;
            case TYPE_CUSTOM:
                popList.addAll(custom);
                break;
            case TYPE_SUPPLIER:
                popList.addAll(gongyingshang);
                break;
            default:
                popList.addAll(privateZiyuan);
                break;
        }
        popAdapter.notifyDataSetChanged();
        userPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //userPopupWindow.showAsDropDown(view);

        //userPopupWindow.sh
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_resources_child;
    }

    @Override
    protected void initData() {
        super.initData();
        initPop();
        pinnedHeaderListView.setIndexBarBackgroundColor(Color.TRANSPARENT);

        resourcesUserAdapter = new UserAdapter(adapterItems, getActivity(), map);

        pinnedHeaderListView.setAdapter(resourcesUserAdapter);


        pinnedHeaderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (resourcesUserAdapter.isItemClickable(i)&&(type==TYPE_MINE||type==TYPE_PUBLIC)) {
                    Intent intent = new Intent(getActivity(), ResourcesUserActivity.class);
                    intent.putExtra("User", adapterItems.get(i));
                    getActivity().startActivity(intent);
                }

                if (resourcesUserAdapter.isItemClickable(i)&&type==TYPE_CUSTOM) {
                    Intent intent = new Intent(getActivity(), CoustomUserActivity.class);
                    intent.putExtra("User", adapterItems.get(i));
                    getActivity().startActivity(intent);
                }

                if (resourcesUserAdapter.isItemClickable(i)&&type==TYPE_SUPPLIER) {
                    Intent intent = new Intent(getActivity(), SupplierUserActivity.class);
                    intent.putExtra("User", adapterItems.get(i));
                    getActivity().startActivity(intent);
                }

            }
        });


        pinnedHeaderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickUser = adapterItems.get(position);
                showUserPop(root);
                return true;
            }
        });
    }


    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {

        itemsAll = TestData.createUser(type);

        userSerachFilter = new UserSerachFilter(itemsAll, new UserSerachFilter.PublishResults() {
            @Override
            public void handler(boolean showBar, List<User> users) {
                handlerEmptyNoRequest();
                pinnedHeaderListView.setIndexBarVisibility(showBar);
                doWork(getResponseListner(), users);
            }
        });

        doWork(responseListner, itemsAll);

    }

    private void doWork(BatterLoadResponseListner responseListner, final List<User> list) {
        BatterTaskItem batterTaskItem = new BatterTaskItem(responseListner, new DoWork() {
            @Override
            public boolean doWorkInThread() {
                isHandlerSuccess = false;
                adapterItems.clear();
                map.clear();
                List<User> temp = ConvertData.convert(list, map, User.class);
                adapterItems.addAll(temp);
                isHandlerSuccess = temp.size() > 0;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();
    }

    @Override
    protected boolean requestSuccess(String result) {
        resourcesUserAdapter.notifyDataSetChanged();
        boolean success = isHandlerSuccess;
        isHandlerSuccess = false;
        return success;
    }


    public void doSerach(UserSerach userSerach) {

        if (userSerachFilter != null) {
            userSerachFilter.filter(gson.toJson(userSerach));
        }
    }







}
