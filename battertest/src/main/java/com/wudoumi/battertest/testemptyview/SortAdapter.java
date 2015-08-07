package com.wudoumi.battertest.testemptyview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.view.listview.listviewfilter.ListHashMap;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedAdapter;
import com.wudoumi.battertest.R;
import com.wudoumi.battertest.bean.TestData;

import java.util.List;

/**
 * Created by qianjujun on 2015/6/30 0030 09:44.
 * qianjujun@163.com
 */
public class SortAdapter extends PinnedAdapter<TestData> {


    public SortAdapter(List<TestData> list, Context context, ListHashMap<String, Integer> validIndexLetters) {
        super(list, context, validIndexLetters);
    }

    @Override
    protected int getTypeSectionLayoutId() {
        return R.layout.section_row_view;
    }

    @Override
    protected int getTypeSectionTextId() {
        return R.id.row_title;
    }

    @Override
    protected View getItemContentView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_view,null);
            viewHolder = new ViewHolder(convertView,R.id.row_title);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(getItem(position).getName());
        return convertView;
    }

    public class ViewHolder {
        public TextView textView;

        public ViewHolder(View conertView,int textId){
            textView = (TextView) conertView.findViewById(textId);
            conertView.setTag(this);
        }

    }
}
