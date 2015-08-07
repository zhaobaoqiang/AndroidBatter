package com.wudoumi.batter.view.listview.listviewfilter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;

import java.util.List;

/**
 * Created by qianjujun on 2015/6/29 0029 12:38.
 * qianjujun@163.com
 */
public abstract class PinnedAdapter<T extends ILetter> extends BatterAdapter<T> implements AbsListView.OnScrollListener, IPinnedHeader, Filterable {

    protected static final int TYPE_ITEM = 0;
    protected static final int TYPE_SECTION = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SECTION + 1;

    int mCurrentSectionPosition = 0, mNextSectionPostion = 0;



    private ListHashMap<String,Integer> validIndexLetters;



    public PinnedAdapter(List<T> list, Context context,ListHashMap<String,Integer> validIndexLetters) {
        super(list, context);
        this.validIndexLetters = validIndexLetters;
    }


    @Override
    public int getPosition(String previewText) {
        if(validIndexLetters.containsKey(previewText)){
            return validIndexLetters.get(previewText);
        }

        return -1;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {

        //return !mListSectionPos.contains(position);

        return !validIndexLetters.containsValue(position);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return validIndexLetters.containsValue(position) ? TYPE_SECTION : TYPE_ITEM;
    }

    @Override
    public int getPinnedHeaderState(int position) {
        // hide pinned header when items count is zero OR position is less than
        // zero OR
        // there is already a header in list view
        if (getCount() == 0 || position < 0 || validIndexLetters.containsValue(position)) {
            return PINNED_HEADER_GONE;
        }

        // the header should get pushed up if the top item shown
        // is the last item in a section for a particular letter.
        mCurrentSectionPosition = getCurrentSectionPosition(position);


        mNextSectionPostion = getNextSectionPosition(mCurrentSectionPosition);

        //Log.d("getCurrent","position"+position+"mCurrentSectionPosition"+mCurrentSectionPosition+"mNextSectionPostion:"+mNextSectionPostion);

        if (mNextSectionPostion != -1 && position == mNextSectionPostion - 1) {
            return PINNED_HEADER_PUSHED_UP;
        }

        return PINNED_HEADER_VISIBLE;
    }

    public int getCurrentSectionPosition(int position) {
        String listChar = list.get(position).getFirstLetter();

        return validIndexLetters.get(listChar);

    }

    public int getNextSectionPosition(int currentSectionPosition) {
        int index = validIndexLetters.indexOf(currentSectionPosition);



        String key = index+1>=validIndexLetters.size()?validIndexLetters.keyList().get(index):validIndexLetters.keyList().get(index+1);

        return validIndexLetters.get(key);
    }

    @Override
    public void configurePinnedHeader(View v, int position) {

        // set text in pinned header
        TextView header = (TextView) v;
        //mCurrentSectionPosition = getCurrentSectionPosition(position);
        header.setText(list.get(position).getFirstLetter());

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
    }

    @Override
    public Filter getFilter() {

        return null;
    }





    protected abstract int getTypeSectionLayoutId();

    protected abstract int getTypeSectionTextId();

    protected abstract View getItemContentView(int position, View convertView, ViewGroup parent);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        int type = getItemViewType(position);
        
        switch (type){
            case TYPE_SECTION:
                ViewHolder holder = null;
                if(convertView==null){
                    convertView = inflater.inflate(getTypeSectionLayoutId(), null);
                    holder = new ViewHolder(convertView,getTypeSectionTextId());
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.textView.setText(getItem(position).getFirstLetter());
                break;
            case TYPE_ITEM:
                return getItemContentView(position,convertView,parent);

        }


        return convertView;
    }





    public class ViewHolder {
        public TextView textView;

        public ViewHolder(View conertView,int textId){
            textView = (TextView) conertView.findViewById(textId);
        }

    }

}
