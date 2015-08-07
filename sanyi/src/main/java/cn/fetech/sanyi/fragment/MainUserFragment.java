package cn.fetech.sanyi.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.PagerSlidingTabStrip;

import java.util.HashMap;
import java.util.Map;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.User;
import cn.fetech.sanyi.bean.UserSerach;

/**
 * Created by qianjujun on 2015/6/24 0024 12:36.
 * qianjujun@163.com
 */
public class MainUserFragment extends BatterFragment{

    public static final int TYPE_RESOURCES = 1;
    public static final int TYPE_COUSTOM = 2;


    private boolean serach = true;


    private int type;

    @ViewInject(R.id.tab)
    private PagerSlidingTabStrip pst;


    @ViewInject(R.id.page)
    private ViewPager viewPager;


    @ViewInject(R.id.et_serach)
    private EditText editTextSerach;


    @ViewInject(R.id.ic_chooce)
    private ImageButton ibChoose;


    private Map<Integer,Fragment> map = new HashMap<>();

    private static String[] currentTitles;

    private static final String[] TITLES_R = new String[]{"我的资源池","公共资源池"};

    private static final String[] TITLES_C = new String[]{"客户","供应商"};



    private PopupWindow statePop;



    private static Map<Integer,Long> timeGapsMap = new HashMap<>();
    private static Map<Integer,Integer> userTypesMap = new HashMap<>();


    static {
        timeGapsMap.put(R.id.week,3600*24*7*1000L);
        timeGapsMap.put(R.id.week2,3600*24*7*2*1000L);
        timeGapsMap.put(R.id.month,3600*24*30*1000L);

        userTypesMap.put(R.id.qianzai,User.TYPE_USER_POTENTIAL);
        userTypesMap.put(R.id.yixiang,User.TYPE_USER_INTENTION);
        userTypesMap.put(R.id.zhun,User.TYPE_USER_QUASI);


    }

    public static MainUserFragment getInstance(int type){
        MainUserFragment fragment = new MainUserFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        switch (type){
            case TYPE_RESOURCES:
                currentTitles = TITLES_R;
                break;
            case TYPE_COUSTOM:
                currentTitles = TITLES_C;
                break;
            default:
                currentTitles = TITLES_R;
                break;
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main_resources,null);
    }


    @Override
    protected void initData() {
        super.initData();
        initPop();

        viewPager.setAdapter(new TabPageAdapter());
        pst.setViewPager(viewPager);

        editTextSerach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!serach){
                    serach = true;
                    return;
                }
                UserSerach userSerach = new UserSerach(editable.toString());
                doSerachWithChild(userSerach);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clearSearchEdittext();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        ibChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statePop!=null){
                    statePop.showAsDropDown(view,0,4);
                }

            }
        });
    }

    private void clearSearchEdittext(){
        serach = false;
        editTextSerach.setText("");
        editTextSerach.setHint("");
    }

    private void initPop(){
        switch (type){
            case TYPE_RESOURCES:
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_huifang_state,null);
                statePop = new PopupWindow(getActivity());
                statePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                statePop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                statePop.setContentView(view);
                statePop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                statePop.setFocusable(true);
                statePop.setOutsideTouchable(false);


                final RadioGroup rgHuifang = (RadioGroup) view.findViewById(R.id.huifang_parent);
                final RadioGroup rgZiyuan = (RadioGroup) view.findViewById(R.id.ziyuan_parent);
                final CheckBox cbAll = (CheckBox) view.findViewById(R.id.state_all);
                cbAll.findViewById(R.id.state_all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(((CheckBox)view).isChecked()){
                            rgHuifang.clearCheck();
                            rgZiyuan.clearCheck();
                        }
                    }
                });

                rgHuifang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.week:
                            case R.id.week2:
                            case R.id.month:
                                break;
                            default:
                                break;
                        }

                        cbAll.setChecked(radioGroup.getCheckedRadioButtonId()==-1);
                    }
                });

                rgZiyuan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.qianzai:
                            case R.id.yixiang:
                            case R.id.zhun:

                                break;
                            default:
                                break;
                        }
                        cbAll.setChecked(radioGroup.getCheckedRadioButtonId()==-1);
                    }
                });

                view.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        statePop.dismiss();
                        if(cbAll.isChecked()){
                            UserSerach userSerach = new UserSerach("");
                            doSerachWithChild(userSerach);
                            return;
                        }
                        long timeGaps = -1;
                        int userType = -1;
                        int gapsId = rgHuifang.getCheckedRadioButtonId();
                        if(gapsId!=-1){
                            timeGaps = timeGapsMap.get(gapsId);
                        }

                        int ziyuanId = rgZiyuan.getCheckedRadioButtonId();

                        if(ziyuanId!=-1){
                            userType = userTypesMap.get(ziyuanId);
                        }
                        UserSerach userSerach = null;
                        if(userType!=-1&&timeGaps==-1){
                            userSerach = new UserSerach(userType);
                        }else if(userType==-1&&timeGaps!=-1){
                            userSerach = new UserSerach(timeGaps);
                        }else if(userType!=-1&&timeGaps!=-1){
                            userSerach = new UserSerach(timeGaps,userType);
                        }

                        if(userSerach!=null){
                            doSerachWithChild(userSerach);
                        }
                    }
                });

                break;


            case TYPE_COUSTOM:
                break;
        }
    }




    class TabPageAdapter extends FragmentPagerAdapter {

        public TabPageAdapter() {

            super(getChildFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {

            return getFragment(position);
        }

        @Override
        public int getCount() {
            return currentTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return currentTitles[position];
        }
    }



    private Fragment getFragment(int position) {


        if(!map.containsKey(position)){
           map.put(position, MainUserChildFragment.getInstance(getChildType(position)));
        }

        return map.get(position);
    }


    private int getChildType(int position){
        switch (type){
            case TYPE_COUSTOM:
                switch (position){
                    case 0:
                        return MainUserChildFragment.TYPE_CUSTOM;
                    case 1:
                        return MainUserChildFragment.TYPE_SUPPLIER;
                }
                break;
            case TYPE_RESOURCES:
                switch (position){
                    case 0:
                        return MainUserChildFragment.TYPE_MINE;
                    case 1:
                        return MainUserChildFragment.TYPE_PUBLIC;
                }
                break;
        }
        return MainUserChildFragment.TYPE_MINE;
    }



    private void doSerachWithChild(UserSerach userSerach){
        if(userSerach.getSearchType()!=UserSerach.TYPE_TEXT){
            clearSearchEdittext();
        }
        editTextSerach.setHint(userSerach.getSearchDescription());
        ((MainUserChildFragment) map.get(viewPager.getCurrentItem())).doSerach(userSerach);
    }



    public void doSerachFormOut(final UserSerach userSerach){
        clearSearchEdittext();
        editTextSerach.setHint(userSerach.getSearchDescription());
        viewPager.setCurrentItem(0);
        int count = viewPager.getChildCount();
        doSerachWithChild(userSerach);

    }




}
