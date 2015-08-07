package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Message;

/**
 * Created by qianjujun on 2015/7/27 0027 15:51.
 * qianjujun@163.com
 */
public class MessageAdapter extends BatterAdapter<Message>{


    public MessageAdapter(List<Message> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Message message = getItem(position);
        Holder holder = null;

        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_message,null);

            holder = new Holder(convertView);
        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.tvTime.setText(message.getTimeStr());
        holder.tvTitle.setText(message.getTitle());
        holder.tvContent.setText(message.getContent());
        return convertView;
    }


    class Holder{
        TextView tvTime,tvTitle,tvContent;

        public Holder(View conertView) {
            tvTime = (TextView) conertView.findViewById(R.id.time);
            tvTitle = (TextView) conertView.findViewById(R.id.title);
            tvContent = (TextView) conertView.findViewById(R.id.content);
            conertView.setTag(this);
        }
    }
}
