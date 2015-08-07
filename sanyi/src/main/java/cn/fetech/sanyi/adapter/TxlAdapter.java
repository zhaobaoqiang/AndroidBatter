package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.TxlParent;

/**
 * Created by qianjujun on 2015/7/20 0020 11:19.
 * qianjujun@163.com
 */
public class TxlAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;

    private List<TxlParent> listParent;


    private Map<Integer,List<TxlParent>> listChild;


    public TxlAdapter(List<TxlParent> listParent,Map<Integer,List<TxlParent>> listChild,Context context) {
        this.listParent = listParent;
        this.listChild = listChild;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {

        return listParent.size();
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        int parentId = listParent.get(groupPosition).getId();

        return listChild.get(parentId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listParent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return listChild.get(listParent.get(groupPosition).getId()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TxlParent txlParent = (TxlParent) getGroup(groupPosition);
        Holder parentHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_txl_parent,null);
            parentHolder = new Holder(convertView);
        }else{
            parentHolder = (Holder) convertView.getTag();
        }
        parentHolder.tvName.setText(txlParent.getName());
        parentHolder.tvNum.setText("("+txlParent.getTotalPeopleNum()+"人)");

        if(isExpanded){
            parentHolder.iv.setImageResource(R.mipmap.icon_expand);
        }else{
            parentHolder.iv.setImageResource(R.mipmap.icon_hint);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Holder childHolder = null;
        TxlParent txlParent = (TxlParent) getChild(groupPosition,childPosition);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_txl_child,null);
            childHolder = new Holder(convertView);
        }else{
            childHolder = (Holder) convertView.getTag();
        }
        childHolder.tvName.setText(txlParent.getName());
        childHolder.tvNum.setText("("+txlParent.getTotalPeopleNum()+"人)");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class Holder{
        TextView tvName,tvNum;
        ImageView iv;
        public Holder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.name);
            tvNum = (TextView) convertView.findViewById(R.id.totalnum);
            iv = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(this);
        }
    }


}
