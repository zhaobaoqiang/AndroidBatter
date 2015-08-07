package cn.fetech.sanyi.widget;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/8/5 0005 16:31.
 * qianjujun@163.com
 */
public class ListStringPop extends BasePop{

    private Context context;

    private ListView listView;

    private List<String> list;

    private AdapterView.OnItemClickListener onItemClickListener;

    public ListStringPop(Context context,List<String> list,AdapterView.OnItemClickListener onItemClickListener) {
        super(context, R.layout.pop_user_menu);
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;

        View conertView = getContentView();

        listView = (ListView) conertView.findViewById(R.id.listview);

        listView.setAdapter(new ArrayAdapter<>(context, R.layout.item_pop_user, R.id.text, list));

        listView.setOnItemClickListener(onItemClickListener);

    }


    public void show(){
        showAtLocation(((Activity)context).getWindow().getDecorView(), Gravity.CENTER,0,0);
    }




}
