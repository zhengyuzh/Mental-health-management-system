package com.mentalhealth.project.system.message_record.domain;

/**
 * 浏览器发送给服务器的 websocket 数据
 */
public class Message {

    private String toName;

    private String message;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "toName='" + toName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
