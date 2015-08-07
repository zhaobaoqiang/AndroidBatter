package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Zhanting;

/**
 * Created by qianjujun on 2015/7/29 0029 15:30.
 * qianjujun@163.com
 */
public class ZhantingAdapter extends BatterAdapter<Zhanting>{

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public ZhantingAdapter(List<Zhanting> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zhanting zhanting = getItem(position);
        Holder holder = null;

        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_zhanting,null);
            holder = new Holder(convertView);
        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.nameTv.setText(zhanting.getJiedaiUser());
        holder.resultTv.setText(zhanting.getResult());
        holder.contentTv.setText(zhanting.getContent());
        holder.timeTv.setText(sdf.format(zhanting.getJiedaiTime()));

        return convertView;
    }


    class Holder{
        TextView nameTv,timeTv,contentTv,resultTv;

        public Holder(View conertView) {
            nameTv = (TextView) conertView.findViewById(R.id.username);
            timeTv = (TextView) conertView.findViewById(R.id.time);
            contentTv = (TextView) conertView.findViewById(R.id.content);
            resultTv = (TextView) conertView.findViewById(R.id.result);
            conertView.setTag(this);
        }
    }
}
