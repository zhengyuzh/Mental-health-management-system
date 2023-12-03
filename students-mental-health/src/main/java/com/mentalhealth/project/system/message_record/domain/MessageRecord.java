package com.mentalhealth.project.system.message_record.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 咨询记录信息对象 sys_message_record
 * 
 * @author zhengyuzhu
 * @date 2021-11-16
 */
public class MessageRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息id */
    private Long messageId;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String loginName;

    /** 接受者 登录账号 */
    @Excel(name = "接受者 登录账号")
    private String toLoginName;

    /** 1.文本， 2.图片 */
    @Excel(name = "1.文本， 2.图片")
    private Integer messageType;

    /** 信息内容 */
    @Excel(name = "信息内容")
    private String messageContent;

    public void setMessageId(Long messageId)
    {
        this.messageId = messageId;
    }

    public Long getMessageId()
    {
        return messageId;
    }
    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginName()
    {
        return loginName;
    }
    public void setToLoginName(String toLoginName)
    {
        this.toLoginName = toLoginName;
    }

    public String getToLoginName()
    {
        return toLoginName;
    }
    public void setMessageType(Integer messageType)
    {
        this.messageType = messageType;
    }

    public Integer getMessageType()
    {
        return messageType;
    }
    public void setMessageContent(String messageContent)
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent()
    {
        return messageContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("loginName", getLoginName())
            .append("toLoginName", getToLoginName())
            .append("messageType", getMessageType())
            .append("messageContent", getMessageContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
