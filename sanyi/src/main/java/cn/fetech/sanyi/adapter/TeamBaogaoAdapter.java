package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wudoumi.batter.utils.ToastUtil;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.TxlUser;

/**
 * Created by qianjujun on 2015/8/6 0006 14:20.
 * qianjujun@163.com
 */
public class TeamBaogaoAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<TxlUser> list;

    private LayoutInflater inflater;

    public TeamBaogaoAdapter(Context context, List<TxlUser> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public TxlUser getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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
        ParentHolder parentHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_parent_baogao,null);
            parentHolder = new ParentHolder(convertView);
        }else{
            parentHolder = (ParentHolder) convertView.getTag();
        }

        parentHolder.tvName.setText(getGroup(groupPosition).getName());

        if(isExpanded){
            parentHolder.icon.setImageResource(R.mipmap.icon_expand);
        }else{
            parentHolder.icon.setImageResource(R.mipmap.icon_hint);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder childHolder = null;



        if(convertView==null){

            convertView = inflater.inflate(R.layout.item_child_baogao,null);
            childHolder = new ChildHolder(convertView);

        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }

        if(groupPosition==0){
            childHolder.zhou.setVisibility(View.GONE);
        }else{
            childHolder.zhou.setVisibility(View.VISIBLE);

            childHolder.zhou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(context,"加载"+getGroup(groupPosition).getName()+"的周报表");
                }
            });
        }

        childHolder.yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(context,"加载"+getGroup(groupPosition).getName()+"的月报表");
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ParentHolder{
        ImageView icon;
        TextView tvName;

        public ParentHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.icon);
            tvName = (TextView) view.findViewById(R.id.name);
            view.setTag(this);
        }
    }

    class ChildHolder{
        LinearLayout zhou,yue;

        public ChildHolder(View view) {
            zhou = (LinearLayout) view.findViewById(R.id.zhoubaobiao);
            yue = (LinearLayout) view.findViewById(R.id.yuebaobiao);
            view.setTag(this);
        }
    }
}
