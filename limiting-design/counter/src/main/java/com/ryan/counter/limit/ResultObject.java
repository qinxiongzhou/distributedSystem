package com.ryan.counter.limit;

/**
* @Description:
* @Author:         zhouqinxiong
* @CreateDate:     2019/8/23 9:21
* @Version:        1.0
*/
public class ResultObject {
    private String code;
    private String message;

    public ResultObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReslutObject{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}