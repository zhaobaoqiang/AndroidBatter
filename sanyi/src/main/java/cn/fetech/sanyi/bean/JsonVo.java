package cn.fetech.sanyi.bean;

/**
 * Created by qianjujun on 2015/8/4 0004 16:39.
 * qianjujun@163.com
 */
public class JsonVo<T> {
    /**
     * 结果
     */
    private boolean success;

    /**
     * 返回的数据
     */
    private T results;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

