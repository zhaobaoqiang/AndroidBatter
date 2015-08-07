// @author Bhavya Mehta
package com.wudoumi.batter.view.listview.listviewfilter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.wudoumi.batter.R;


// Represents right side index bar view with unique first latter of list view row text 
public class IndexBarView extends View {

    final static String TAG = "IndexBarView";

    final static String[] indexLetter = new String[]{"#","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    // index bar margin
    float mIndexbarMargin;

    // user touched Y axis coordinate value
    float mSideIndexY;

    // flag used in touch events manipulations
    boolean mIsIndexing = false;

    // holds current section position selected by user
    int mCurrentSectionPosition = -1;

    // array list to store listView data
    ArrayList<String> mListItems;

    // paint object
    Paint mIndexPaint;

    // context object
    Context mContext;

    // interface object used as bridge between list view and index bar view for
    // filtering list view content on touch event
    IIndexBarFilter mIndexBarFilter;






    public IndexBarView(Context context) {
        super(context);
        this.mContext = context;
    }


    public IndexBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }


    public IndexBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    private void init(){
        // set index bar margin from resources
        mIndexbarMargin = mContext.getResources().getDimension(R.dimen.index_bar_view_margin);
        // index bar item color and text size
        mIndexPaint = new Paint();
        mIndexPaint.setColor(mContext.getResources().getColor(R.color.black));
        mIndexPaint.setAntiAlias(true);
        mIndexPaint.setTextSize(mContext.getResources().getDimension(R.dimen.index_bar_view_text_size));
    }





    public void setIIndexBarFilter(IIndexBarFilter indexBarFilter){
        this.mIndexBarFilter = indexBarFilter;
    }


    // draw view content on canvas using paint
    @Override
    protected void onDraw(Canvas canvas) {
        if (indexLetter != null && indexLetter.length > 1) {
            float sectionHeight = (getMeasuredHeight() - 2 * mIndexbarMargin)/ indexLetter.length;
            float paddingTop = (sectionHeight - (mIndexPaint.descent() - mIndexPaint.ascent())) / 2;

            for(int i = 0;i<indexLetter.length;i++){
                float paddingLeft = (getMeasuredWidth() - mIndexPaint.measureText(indexLetter[i])) / 2;

                canvas.drawText(indexLetter[i],
                        paddingLeft,
                        mIndexbarMargin + (sectionHeight * i) + paddingTop + mIndexPaint.descent(),
                        mIndexPaint);
            }
        }
        super.onDraw(canvas);
    }





    boolean contains(float x, float y) {
        // Determine if the point is in index bar region, which includes the
        // right margin of the bar
        return (x >= getLeft() && y >= getTop() && y <= getTop() + getMeasuredHeight());
    }


    void filterListItem(float sideIndexY) {
        mSideIndexY = sideIndexY;

        // filter list items and get touched section position with in index bar
        mCurrentSectionPosition = (int) (((mSideIndexY) - getTop() - mIndexbarMargin) /
                                    ((getMeasuredHeight() - (2 * mIndexbarMargin)) / indexLetter.length));


        Log.d(TAG,"mCurrentSectionPosition:"+mCurrentSectionPosition);
        if(mCurrentSectionPosition >= 0 && mCurrentSectionPosition < indexLetter.length){
            String previewText = indexLetter[mCurrentSectionPosition];
            mIndexBarFilter.filterList(mSideIndexY,previewText);
        }

    }


    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                // If down event occurs inside index bar region, start indexing
                if (contains(ev.getX(), ev.getY())) {
                    // It demonstrates that the motion event started from index
                    // bar
                    mIsIndexing = true;
                    // Determine which section the point is in, and move the
                    // list to
                    // that section
                    filterListItem(ev.getY());
                    return true;
                }
                else {
                    mCurrentSectionPosition = -1;
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                if (mIsIndexing) {
                    // If this event moves inside index bar
                    if (contains(ev.getX(), ev.getY())) {
                        // Determine which section the point is in, and move the
                        // list to that section
                        filterListItem(ev.getY());
                        return true;
                    }
                    else {
                        mCurrentSectionPosition = -1;
                        return false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsIndexing) {
                    mIsIndexing = false;
                    mCurrentSectionPosition = -1;
                }
                break;
        }
        return false;
    }
}
