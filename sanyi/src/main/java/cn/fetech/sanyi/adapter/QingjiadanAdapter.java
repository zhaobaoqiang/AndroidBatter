package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Qingjiadan;

/**
 * Created by qianjujun on 2015/8/4 0004 11:42.
 * qianjujun@163.com
 */
public class QingjiadanAdapter extends BatterAdapter<Qingjiadan>{
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public QingjiadanAdapter(List<Qingjiadan> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Qingjiadan qingjiadan = getItem(position);
        Holder holder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_qingjiadan,null);
            holder = new Holder(convertView);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvTime.setText(simpleDateFormat.format(qingjiadan.getCreateTime()));
        holder.tvContent.setText(qingjiadan.getContent());
        holder.tvTitle.setText(qingjiadan.getTitle());
        holder.tvState.setText(qingjiadan.getState());
        holder.tvReciver.setText(qingjiadan.getReciverName());
        holder.tvTotalTime.setText(qingjiadan.getTime());
        return convertView;
    }

    class Holder{
        TextView tvTime,tvState,tvTotalTime,tvContent,tvReciver,tvTitle;


        public Holder(View view) {
            tvTime = (TextView) view.findViewById(R.id.time);
            tvState = (TextView) view.findViewById(R.id.state);
            tvTotalTime = (TextView) view.findViewById(R.id.totalTime);
            tvContent = (TextView) view.findViewById(R.id.content);
            tvReciver = (TextView) view.findViewById(R.id.reciver);
            tvTitle = (TextView) view.findViewById(R.id.title);
            view.setTag(this);
        }
    }
}
