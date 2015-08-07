package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Goods;
import cn.fetech.sanyi.bean.Order;

/**
 * Created by qianjujun on 2015/7/29 0029 17:05.
 * qianjujun@163.com
 */
public class OrderAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;

    private List<Order> list;


    private int pading;


    public OrderAdapter(List<Order> list,Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);

        pading = context.getResources().getDimensionPixelOffset(R.dimen.q20);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getList().size();
    }

    @Override
    public Order getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Goods getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getList().get(childPosition);
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
        Order order = getGroup(groupPosition);
        HolderParent holderParent = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_order_parent,null);
            holderParent = new HolderParent(convertView);
        }else{
            holderParent = (HolderParent) convertView.getTag();
        }

        if(isExpanded){
            holderParent.iv.setImageResource(R.mipmap.icon_expand);
            holderParent.root.setBackgroundResource(R.drawable.bg_order_parent_expand);
        }else{
            holderParent.iv.setImageResource(R.mipmap.icon_hint);
            holderParent.root.setBackgroundResource(R.drawable.bg_zhanting_item);
        }
        holderParent.root.setPadding(pading,pading,pading,pading);
        holderParent.tvOrderNo.setText(order.getOrderNo());
        holderParent.tvOrderPrice.setText("￥"+order.getTotalPrice()+"元");
        holderParent.tvState.setText(order.getStateName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Goods goods = getChild(groupPosition,childPosition);
        HolderChild holderChild = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_order_child,null);
            holderChild = new HolderChild(convertView);
        }else{
            holderChild = (HolderChild) convertView.getTag();
        }

        if(childPosition==getChildrenCount(groupPosition)-1){
            holderChild.root.setBackgroundResource(R.drawable.bg_order_child);
        }else{
            holderChild.root.setBackgroundResource(R.drawable.bg_order_child_middle);
        }
        holderChild.root.setPadding(pading,pading,pading,pading);

        holderChild.tvNo.setText(goods.getGoodsNo());
        holderChild.tvName.setText(goods.getName());
        holderChild.tvPrice.setText("￥"+goods.getConsignmentPrice()+"元");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }




    class HolderParent{
        View root;
        ImageView iv;

        TextView tvOrderPrice,tvState,tvOrderNo;

        public HolderParent(View conertView) {
            root = conertView.findViewById(R.id.root);
            iv = (ImageView) conertView.findViewById(R.id.icon);
            tvOrderPrice = (TextView) conertView.findViewById(R.id.total_price);
            tvState = (TextView) conertView.findViewById(R.id.state);
            tvOrderNo = (TextView) conertView.findViewById(R.id.oreder_no);
            conertView.setTag(this);
        }
    }

    class HolderChild{
        View root;
        ImageView iv;
        TextView tvNo,tvName,tvPrice;

        public HolderChild(View view) {
            root = view.findViewById(R.id.root);
            iv = (ImageView) view.findViewById(R.id.icon);
            tvNo = (TextView) view.findViewById(R.id.goods_no);
            tvName = (TextView) view.findViewById(R.id.goods_name);
            tvPrice = (TextView) view.findViewById(R.id.goods_price);
            view.setTag(this);
        }
    }
}
