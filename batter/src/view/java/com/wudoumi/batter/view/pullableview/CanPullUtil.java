package com.wudoumi.batter.view.pullableview;

import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;

/**
 * Created by Administrator on 2015/6/17 0017.
 */
public class CanPullUtil {
    public enum ViewType{
        PULLABLE,ADAPTERVIEW,WEBVIEW,OTHERVIEW
    }
    public static boolean canPullDown(ViewType mViewType,View view){
        switch (mViewType){
            case PULLABLE:
                return ((Pullable)view).canPullDown();
            case ADAPTERVIEW:
                return canPullDown((AdapterView)view);
            case WEBVIEW:
                return canPullDown((WebView)view);
            case OTHERVIEW:
                return true;

        }
        return false;
    }


    public static boolean canPullUp(ViewType mViewType,View view){
        switch (mViewType){
            case PULLABLE:
                return ((Pullable)view).canPullUp();
            case ADAPTERVIEW:
                return canPullUp((AdapterView) view);
            case WEBVIEW:
                return canPullUp((WebView)view);
            case OTHERVIEW:
                return true;

        }
        return false;
    }



    static boolean canPullDown(AdapterView view){
        if (view.getCount() == 0)
        {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (view.getFirstVisiblePosition() == 0
                && view.getChildAt(0).getTop() >= 0)
        {
            // 滑到顶部了
            return true;
        } else
            return false;
    }

    static boolean canPullUp(AdapterView view){
        if (view.getCount() == 0)
        {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (view.getLastVisiblePosition() == (view.getCount() - 1))
        {
            // 滑到底部了
            if (view.getChildAt(view.getLastVisiblePosition() - view.getFirstVisiblePosition()) != null
                    && view.getChildAt(
                    view.getLastVisiblePosition()
                            - view.getFirstVisiblePosition()).getBottom() <= view.getMeasuredHeight())
                return true;
        }
        return false;
    }

    static boolean canPullDown(WebView view){
        if (view.getScrollY() == 0)
            return true;
        else
            return false;
    }

    static boolean canPullUp(WebView view){
        if (view.getScrollY() >= view.getContentHeight() * view.getScale()
                - view.getMeasuredHeight())
            return true;
        else
            return false;
    }
}
