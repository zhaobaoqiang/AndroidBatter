package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.ContractJieSuan;

/**
 * Created by qianjujun on 2015/7/30 0030 16:00.
 * qianjujun@163.com
 */
public class ContractJieSuanAdapter extends BatterAdapter<ContractJieSuan>{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public ContractJieSuanAdapter(List<ContractJieSuan> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContractJieSuan contractJieSuan = getItem(position);
        Holder holder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_contract_jiesuan,null);
            holder = new Holder(convertView);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.tvTime.setText(sdf.format(contractJieSuan.getTime()));
        holder.tvOrderNo.setText(contractJieSuan.getGoodsNo());
        holder.tvYingshou.setText(getMoney(contractJieSuan.getYingshou()));
        holder.tvYishou.setText(getMoney(contractJieSuan.getYishou()));
        holder.tvWeikuan.setText(getMoney(contractJieSuan.getWeikuan()));
        holder.tvFujiafei.setText(contractJieSuan.getFujiafeiStr());
        return convertView;
    }

    private String getMoney(double d){
        return "￥"+d;
    }

    class Holder{
        TextView tvTime,tvOrderNo,tvYingshou,tvYishou,tvWeikuan,tvFujiafei;

        public Holder(View conertView) {
            tvTime = (TextView) conertView.findViewById(R.id.time);
            tvOrderNo = (TextView) conertView.findViewById(R.id.goodsNo);
            tvYingshou = (TextView) conertView.findViewById(R.id.yingshou);
            tvYishou = (TextView) conertView.findViewById(R.id.yishou);
            tvWeikuan = (TextView) conertView.findViewById(R.id.weikuan);
            tvFujiafei = (TextView) conertView.findViewById(R.id.fujiafei);
            conertView.setTag(this);
        }
    }
}
