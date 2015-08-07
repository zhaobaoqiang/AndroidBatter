package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Fuwu;

/**
 * Created by qianjujun on 2015/7/30 0030 14:17.
 * qianjujun@163.com
 */
public class FuwuAdapter extends BatterAdapter<Fuwu>{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public FuwuAdapter(List<Fuwu> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fuwu fuwu = getItem(position);
        Holder holder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_fuwu,null);
            holder = new Holder(convertView);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.tvContent.setText(fuwu.getContent());
        holder.tvState.setText(fuwu.getState());
        holder.tvTime.setText(sdf.format(fuwu.getTime()));
        holder.tvTitle.setText(fuwu.getTitle());
        return convertView;
    }

    class Holder{
        TextView tvTitle,tvTime,tvState,tvContent;

        public Holder(View conertView) {
            tvTitle = (TextView) conertView.findViewById(R.id.title);
            tvTime = (TextView) conertView.findViewById(R.id.time);
            tvState = (TextView) conertView.findViewById(R.id.state);
            tvContent = (TextView) conertView.findViewById(R.id.content);
            conertView.setTag(this);
        }
    }
}
