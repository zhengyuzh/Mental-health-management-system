package com.mentalhealth.project.system.message_record.domain;

/**
 * 服务器发送给浏览器的 websocket 数据
 */
public class ResultMessage {

    private boolean isSystem;
    private String fromName;
    private Object message;  //如果是系统消息是数组 {"message":["李四"],["王五"]}

    public boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "isSystem=" + isSystem +
                ", fromName='" + fromName + '\'' +
                ", message=" + message +
                '}';
    }
}
