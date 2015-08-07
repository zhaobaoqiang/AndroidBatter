package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.OrderAnalysis;

/**
 * Created by qianjujun on 2015/7/29 0029 16:03.
 * qianjujun@163.com
 */
public class OrderAnalysisAdapter extends BatterAdapter<OrderAnalysis>{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public OrderAnalysisAdapter(List<OrderAnalysis> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAnalysis orderAnalysis = getItem(position);
        Holder holder = null;

        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_order_analysis,null);
            holder = new Holder(convertView);
        }else {
            holder = (Holder) convertView.getTag();
        }

        holder.contentTv.setText(orderAnalysis.getContent());
        holder.timeTv.setText(sdf.format(orderAnalysis.getTime()));
        holder.typeTv.setText(orderAnalysis.getType());
        return convertView;
    }

    class Holder{
        TextView timeTv,typeTv,contentTv;

        public Holder(View conertView) {
            timeTv = (TextView) conertView.findViewById(R.id.time);
            typeTv = (TextView) conertView.findViewById(R.id.type);
            contentTv = (TextView) conertView.findViewById(R.id.content);
            conertView.setTag(this);
        }
    }
}
