package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.FollowUp;

/**
 * Created by qianjujun on 2015/7/6 0006 16:37.
 * qianjujun@163.com
 */
public class FollowAdapter extends BatterAdapter<FollowUp> {
    public FollowAdapter(List<FollowUp> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder = null;
        FollowUp followUp = getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.item_followup, null);
            holder = new Holder(view);
        }else{
            holder = (Holder) view.getTag();
        }
        if(position==0){
            holder.topFengexian.setVisibility(View.INVISIBLE);
            holder.ivLast.setImageResource(R.mipmap.icon_follow_last);
        }else{
            holder.topFengexian.setVisibility(View.GONE);
            holder.ivLast.setImageResource(R.mipmap.icon_follow_before);
        }

        holder.tvTime.setText(followUp.getTimeStr());

        holder.ivState.setImageResource(followUp.getStateIconRes());

        holder.tvContent.setText(followUp.getContent());
        return view;
    }

    class Holder {
        ImageView ivState, ivLast;
        TextView tvTime, tvContent;
        View topFengexian;

        public Holder(View view) {
            ivState = (ImageView) view.findViewById(R.id.iv_state);
            ivLast = (ImageView) view.findViewById(R.id.iv_last);
            tvTime = (TextView) view.findViewById(R.id.time);
            tvContent = (TextView) view.findViewById(R.id.content);
            topFengexian = view.findViewById(R.id.top_fengexian);
            view.setTag(this);
        }
    }
}
