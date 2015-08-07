package com.wudoumi.battertest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.wudoumi.batter.utils.PreferenceHelper;
import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.thread.BatterTask1;
import com.wudoumi.batter.thread.BatterTaskItem1;
import com.wudoumi.batter.thread.BatterTaskListener;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;
import com.wudoumi.batter.view.pullableview.State;
import com.wudoumi.battertest.bean.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class DbFragment extends BatterFragment {


    @ViewInject(R.id.listView)
    ListView mListView;

    @ViewInject(R.id.refresh_view)
    PullToRefreshLayout mPullToRefreshLayout;


    private DataBaseHelper databaseHelper = null;
    Dao<User, Integer> dao = null;

    private UserAdapter userAdapter;
    private List<User> list = new ArrayList<User>();

    private Random random = new Random();

    private static int userCode = 1;
    private static final String USERCODE = "UserCode";


    private static String[] defauthUserNames = new String[]{"张三", "李四", "王武", "曾留"};


    private long lastTime = 0;


    private boolean first = true;


    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userCode = PreferenceHelper.getInstance().getInt(USERCODE, 1);

        databaseHelper = DataBaseHelper.getHelper();
        try {
            dao = DataBaseHelper.getHelper().getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        DataBaseHelper.relaseHelper();

        PreferenceHelper.getInstance().put(USERCODE, userCode);

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_db, null);
        return view;
    }

    @Override
    protected void onCreateViewEnd() {
        super.onCreateViewEnd();

        userAdapter = new UserAdapter(list, getActivity());
        mListView.setAdapter(userAdapter);

        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                if (first) {
                    firstRequest(pullToRefreshLayout);
                    first = false;
                } else {
                    addUserList(pullToRefreshLayout);
                }
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                loadMore(pullToRefreshLayout);
            }
        });

        mPullToRefreshLayout.refrush();


    }


    private void addUserList(final PullToRefreshLayout pullToRefreshLayout) {

        BatterTask1 mBatterTask1 = new BatterTask1();
        BatterTaskItem1 mBatterTaskItem1 = new BatterTaskItem1();
        mBatterTaskItem1.setListener(new BatterTaskListener() {
            List<User> users = null;

            @Override
            public void get() {
                super.get();
                updateLastTime();
                try {
                    users = dao.queryBuilder().orderBy("millis", true).limit(5L).offset(0L).where().gt("millis", lastTime).query();
                    if (users == null || users.size() == 0) {
                        createUsers();
                    } else {
                        Collections.reverse(users);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void update() {
                super.update();
                updateAdapter(users, pullToRefreshLayout,true);
            }


        });

        mBatterTask1.execute(mBatterTaskItem1);

    }

    private void loadMore(final PullToRefreshLayout pullToRefreshLayout){
        BatterTask1 mBatterTask1 = new BatterTask1();
        BatterTaskItem1 mBatterTaskItem1 = new BatterTaskItem1();
        mBatterTaskItem1.setListener(new BatterTaskListener() {
            List<User> users = null;

            @Override
            public void get() {
                super.get();
                //updateLastTime();
                try {
                    users = dao.queryBuilder().orderBy("millis", false).limit(5L).offset(0L).where().lt("millis", updateOldTime()).query();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void update() {
                super.update();
                updateAdapter(users, pullToRefreshLayout,false);
            }


        });

        mBatterTask1.execute(mBatterTaskItem1);

    }


    private void firstRequest(final PullToRefreshLayout pullToRefreshLayout) {
        BatterTask1 mBatterTask1 = new BatterTask1();
        BatterTaskItem1 mBatterTaskItem1 = new BatterTaskItem1();
        mBatterTaskItem1.setListener(new BatterTaskListener(){
            List<User> users = null;
            @Override
            public void get() {
                super.get();
                updateLastTime();
                //降序
                try {
                    users = dao.queryBuilder().orderBy("millis", false).limit(10L).offset(0L).where().gt("millis", lastTime).query();
                    if(users==null|| users.size()==0){
                        createUsers();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void update() {
                super.update();
                updateAdapter(users,pullToRefreshLayout,true);
            }


        });


        mBatterTask1.execute(mBatterTaskItem1);


    }





    private void updateAdapter(List<User> users,PullToRefreshLayout pullToRefreshLayout,boolean refresh){
        if (users == null || users.size() == 0) {
            if(refresh){
                pullToRefreshLayout.refreshFinish(State.FAIL);
            }else{
                pullToRefreshLayout.loadmoreFinish(State.SUCCEED);
            }


        } else {
            if(refresh){
                list.addAll(0, users);
                pullToRefreshLayout.refreshFinish(State.SUCCEED);
                userAdapter.notifyDataSetChanged();
            }else{
                //int size = list.size();
                list.addAll(users);
                pullToRefreshLayout.loadmoreFinish(State.SUCCEED);
                userAdapter.notifyDataSetChanged();
                mListView.setSelection(list.size() - users.size());
            }



        }
    }

    private void updateLastTime() {
        if (list.size() == 0) {
            lastTime = 0;
        } else {
            lastTime = list.get(0).getMillis();
        }
    }

    private long updateOldTime(){
        if(list.size()==0){
            return 0;
        }
        return list.get(list.size()-1).getMillis();
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<User>();
        int maxLenth = random.nextInt(14) + 1;
        User user = null;
        for (int i = 0; i < maxLenth; i++) {
            user = new User(defauthUserNames[random.nextInt(defauthUserNames.length)] + ++userCode);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            users.add(user);
        }

        try {
            dao.create(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }


    static class UserAdapter extends BatterAdapter<User> {

        public UserAdapter(List<User> list, Context context) {
            super(list, context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder mHolder = null;
            User user = getItem(position);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_db_user, null);
                mHolder = new Holder(convertView);
                convertView.setTag(mHolder);
            } else {
                mHolder = (Holder) convertView.getTag();
            }
            mHolder.tvId.setText(user.get_id() + "");
            mHolder.tvName.setText(user.getName());
            mHolder.tvTime.setText(user.getTime()+"");
            return convertView;
        }


        static class Holder {
            TextView tvId, tvName, tvTime;

            public Holder(View view) {
                tvId = (TextView) view.findViewById(R.id.userID);
                tvName = (TextView) view.findViewById(R.id.userName);
                tvTime = (TextView) view.findViewById(R.id.dateTime);

            }
        }
    }


}
