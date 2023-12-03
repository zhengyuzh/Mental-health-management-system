package com.mentalhealth.project.system.message_record.domain;

/**
 * 用于登录响应回给浏览器的数据
 *
 * */
public class Result {

    private boolean flag;
    private String message;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                '}';
    }
}
