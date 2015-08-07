package cn.fetech.sanyi;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.base.BatterFragment;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.activity.JobBaogaoActivity;
import cn.fetech.sanyi.activity.MimeActivity;
import cn.fetech.sanyi.activity.SettingActivity;

/**
 * Created by qianjujun on 2015/6/24 0024 10:26.
 * qianjujun@163.com
 */
public class SlideFragment extends BatterFragment {


    @ViewInject(R.id.listview)
    private ListView mListView;

    @ViewInject(R.id.exit)
    private TextView tvExit;

    @ViewInject(R.id.setting)
    private TextView tvSet;


    private List<MenuItem> menuItems = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_slide;
    }

    @Override
    protected void initData() {
        super.initData();
        initMenu();
        mListView.setAdapter(new MenuAdapter(menuItems, getActivity()));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = menuItems.get(position).getAction();
                if(intent==null){
                    ToastUtil.showToast(getActivity(),"暂未实现");
                }else {
                    startActivity(intent);
                }
            }
        });


        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).exit();
            }
        });

        tvSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

    }

    private void initMenu() {
        Intent intent = null;

        MenuItem menuItem = new MenuItem(R.mipmap.menu_job_baogao, R.string.menu1);
        menuItem.setAction(new Intent(getActivity(),JobBaogaoActivity.class));
        menuItems.add(menuItem);

        //menuItem = new MenuItem(R.mipmap.menu_jixiao_kaohe, R.string.menu2);

        //menuItems.add(menuItem);

        menuItem = new MenuItem(R.mipmap.menu_yibiaopan, R.string.menu3);

        menuItems.add(menuItem);

//        menuItem = new MenuItem(R.mipmap.menu_tongbu, R.string.menu4);
//
//        menuItems.add(menuItem);

        menuItem = new MenuItem(R.mipmap.menu_mine, R.string.menu5);
        menuItem.setAction(new Intent(getActivity(),MimeActivity.class));
        menuItems.add(menuItem);
    }


    class MenuItem {
        private int title;
        private int icon;

        private Intent action;

        public int getTitle() {
            return title;
        }

        public void setTitle(int title) {
            this.title = title;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public Intent getAction() {
            return action;
        }

        public void setAction(Intent action) {
            this.action = action;
        }

        public MenuItem(int icon, int title) {
            this.title = title;
            this.icon = icon;
        }
    }


    static class MenuAdapter extends BatterAdapter<MenuItem> {

        public MenuAdapter(List<MenuItem> list, Context context) {
            super(list, context);
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            MenuItem menuItem = getItem(position);
            Holder holder = null;
            if (view == null) {
                view = inflater.inflate(R.layout.item_menu, null);
                holder = new Holder(view);
            } else {
                holder = (Holder) view.getTag();
            }

            holder.icon.setImageResource(menuItem.getIcon());
            holder.title.setText(menuItem.getTitle());
            return view;
        }

        static class Holder {
            ImageView icon;
            TextView title;

            public Holder(View view) {
                icon = (ImageView) view.findViewById(R.id.icon);
                title = (TextView) view.findViewById(R.id.title);
                view.setTag(this);
            }
        }
    }
}
