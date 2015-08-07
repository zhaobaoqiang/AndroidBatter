package com.wudoumi.batter.net;

/**
 * Created by qianjujun on 2015/6/30 0030 16:47.
 * qianjujun@163.com
 */
public interface NetSuccessHandler<T> {
    T convert(String json);

    boolean handlerResultInThread(T t);

    void onUIReciverResult(T t);
}
