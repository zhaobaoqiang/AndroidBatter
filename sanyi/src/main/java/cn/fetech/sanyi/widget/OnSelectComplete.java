package cn.fetech.sanyi.widget;

import android.view.View;

/**
 * Created by qianjujun on 2015/7/13 0013 14:15.
 * qianjujun@163.com
 */
public interface OnSelectComplete<T> {
    void onChoose(T result);

    View getRootView();
}
