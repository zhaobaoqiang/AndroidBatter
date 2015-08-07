package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Task;

/**
 * Created by qianjujun on 2015/6/25 0025 12:26.
 * qianjujun@163.com
 */
public class TaskAdapter extends BatterAdapter<Task>{

    public TaskAdapter(List<Task> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Task task = getItem(position);
        Holder holder = null;

        if(view==null){
            view = inflater.inflate(R.layout.item_task,null);
            holder = new Holder(view);
        }else{
            holder = (Holder) view.getTag();
        }

        holder.tvStartTime.setText(task.getStartTimeStr());
        holder.tvEndTime.setText(task.getEndTimeStr());
        holder.tvTaskContent.setText(task.getTaskContent());
        holder.tvTaskFrom.setText(task.getForm());

        holder.ivImportance.setVisibility(task.isImportance()?View.VISIBLE:View.INVISIBLE);
        holder.ivPriority.setImageResource(task.isPriority()?View.VISIBLE:View.INVISIBLE);

        holder.tvState.setText(task.getState());
        return view;
    }


    static class Holder{
        TextView tvStartTime,tvEndTime,tvTaskContent,tvTaskFrom,tvState;
        ImageView ivImportance,ivPriority;


        public Holder(View view){
            tvStartTime = (TextView) view.findViewById(R.id.start_time);
            tvEndTime = (TextView) view.findViewById(R.id.end_time);
            tvTaskContent = (TextView) view.findViewById(R.id.task_content);
            tvTaskFrom = (TextView) view.findViewById(R.id.task_from);

            ivImportance = (ImageView) view.findViewById(R.id.icon_importance);
            ivPriority = (ImageView) view.findViewById(R.id.icon_priority);

            tvState = (TextView) view.findViewById(R.id.state);
            view.setTag(this);

        }
    }
}
