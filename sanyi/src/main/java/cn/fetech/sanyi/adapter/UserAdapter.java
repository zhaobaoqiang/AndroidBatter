package cn.fetech.sanyi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wudoumi.batter.view.listview.listviewfilter.ListHashMap;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedAdapter;

import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.User;

/**
 * Created by qianjujun on 2015/8/4 0004 15:40.
 * qianjujun@163.com
 */
public class UserAdapter extends PinnedAdapter<User> {
    public UserAdapter(List<User> list, Context context, ListHashMap<String, Integer> validIndexLetters) {
        super(list, context, validIndexLetters);
    }

    @Override
    protected int getTypeSectionLayoutId() {
        return R.layout.item_section_row_view;
    }

    @Override
    protected int getTypeSectionTextId() {
        return R.id.row_title;
    }

    @Override
    protected View getItemContentView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_resources_user, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(user.getName());
        viewHolder.tvPhone.setText(user.getPhone());
        viewHolder.tvTop.setText(user.getTopTips());
        viewHolder.tvBottom.setText(user.getBottomTips());
        return convertView;
    }

    public boolean isItemClickable(int position) {
        return getItemViewType(position) == TYPE_ITEM;
    }

    class ViewHolder {
        TextView tvName, tvPhone, tvTop, tvBottom;
        ImageView ivHead;

        public ViewHolder(View conertView) {
            tvName = (TextView) conertView.findViewById(R.id.name);
            tvPhone = (TextView) conertView.findViewById(R.id.phone);
            tvTop = (TextView) conertView.findViewById(R.id.top);
            tvBottom = (TextView) conertView.findViewById(R.id.bottom);
            conertView.setTag(this);
        }
    }
}
