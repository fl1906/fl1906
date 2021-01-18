package com.fl.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/18/16:47
 * @Description:
 */
public class WeiXin {

    private Result result;
    private String type;
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public WeiXin(Result result, String type) {
        super();
        this.result = result;
        this.type = type;
    }
    public WeiXin() {
        super();
    }
}
