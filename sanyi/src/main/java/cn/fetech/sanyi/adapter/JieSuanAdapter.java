package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.OrederJieSuan;

/**
 * Created by qianjujun on 2015/7/30 0030 11:16.
 * qianjujun@163.com
 */
public class JieSuanAdapter extends BatterAdapter<OrederJieSuan>{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public JieSuanAdapter(List<OrederJieSuan> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrederJieSuan orederJieSuan = getItem(position);
        Holder holder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_jiesuan,null);
            holder = new Holder(convertView);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.tvTime.setText(sdf.format(orederJieSuan.getTime()));
        holder.tvOrderNo.setText(orederJieSuan.getOrderNo());
        holder.tvYingshou.setText(getMoney(orederJieSuan.getYingshou()));
        holder.tvYishou.setText(getMoney(orederJieSuan.getYishou()));
        holder.tvWeikuan.setText(getMoney(orederJieSuan.getWeikuan()));
        holder.tvDingjin.setText(getMoney(orederJieSuan.getDingjin()));
        return convertView;
    }


    private String getMoney(double d){
        return "￥"+d;
    }

    class Holder{
        TextView tvTime,tvOrderNo,tvYingshou,tvYishou,tvWeikuan,tvDingjin;

        public Holder(View conertView) {
            tvTime = (TextView) conertView.findViewById(R.id.time);
            tvOrderNo = (TextView) conertView.findViewById(R.id.orderNo);
            tvYingshou = (TextView) conertView.findViewById(R.id.yingshou);
            tvYishou = (TextView) conertView.findViewById(R.id.yishou);
            tvWeikuan = (TextView) conertView.findViewById(R.id.weikuan);
            tvDingjin = (TextView) conertView.findViewById(R.id.dingjin);
            conertView.setTag(this);
        }
    }
}
