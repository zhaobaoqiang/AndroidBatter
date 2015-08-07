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
import cn.fetech.sanyi.bean.Contract;
import cn.fetech.sanyi.bean.Goods;

/**
 * Created by qianjujun on 2015/7/30 0030 15:01.
 * qianjujun@163.com
 */
public class ContractAdapter extends BaseExpandableListAdapter{

    private LayoutInflater inflater;

    private List<Contract> list;


    private int pading;


    public ContractAdapter(List<Contract> list,Context context) {
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
    public Contract getGroup(int groupPosition) {
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
        Contract contract = getGroup(groupPosition);
        HolderParent holderParent = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_contract_parent,null);
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
        holderParent.tvOrderNo.setText(contract.getContractNo());

        holderParent.tvState.setText(contract.getState());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Goods goods = getChild(groupPosition,childPosition);

        MyClick myClick = new MyClick(getGroup(groupPosition),goods);
        HolderChild holderChild = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_contract_child,null);
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

        holderChild.tvChangePrice.setOnClickListener(myClick);
        holderChild.tvQuhuo.setOnClickListener(myClick);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class HolderParent{
        View root;
        ImageView iv;

        TextView tvState,tvOrderNo;

        public HolderParent(View conertView) {
            root = conertView.findViewById(R.id.root);
            iv = (ImageView) conertView.findViewById(R.id.icon);
            tvState = (TextView) conertView.findViewById(R.id.state);
            tvOrderNo = (TextView) conertView.findViewById(R.id.oreder_no);
            conertView.setTag(this);
        }
    }

    class HolderChild{
        View root;
        ImageView iv;
        TextView tvNo,tvName,tvPrice;

        TextView tvChangePrice,tvQuhuo;


        public HolderChild(View view) {
            root = view.findViewById(R.id.root);
            iv = (ImageView) view.findViewById(R.id.icon);
            tvNo = (TextView) view.findViewById(R.id.goods_no);
            tvName = (TextView) view.findViewById(R.id.goods_name);
            tvPrice = (TextView) view.findViewById(R.id.goods_price);
            tvChangePrice = (TextView) view.findViewById(R.id.changePrice);
            tvQuhuo = (TextView) view.findViewById(R.id.quhuo);
            view.setTag(this);
        }
    }


    class MyClick implements View.OnClickListener{

        private Contract contract;

        private Goods goods;


        public MyClick(Contract contract, Goods goods) {
            this.contract = contract;
            this.goods = goods;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.changePrice:
                    //ToastUtil.showToast();
                    break;
                case R.id.quhuo:
                    break;
            }
        }
    }
}
