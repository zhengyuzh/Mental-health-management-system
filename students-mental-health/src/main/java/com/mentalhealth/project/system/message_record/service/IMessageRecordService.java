package com.mentalhealth.project.system.message_record.service;

import com.mentalhealth.project.system.message_record.domain.MessageRecord;

import java.util.List;

/**
 * 咨询记录信息Service接口
 * 
 * @author zhengyuzhu
 * @date 2021-11-16
 */
public interface IMessageRecordService 
{
    /**
     * 查询咨询记录信息
     * 
     * @param messageId 咨询记录信息主键
     * @return 咨询记录信息
     */
    public MessageRecord selectMessageRecordByMessageId(Long messageId);

    /**
     * 查询咨询记录信息列表
     * 
     * @param messageRecord 咨询记录信息
     * @return 咨询记录信息集合
     */
    public List<MessageRecord> selectMessageRecordList(MessageRecord messageRecord);

    /**
     * 新增咨询记录信息
     * 
     * @param messageRecord 咨询记录信息
     * @return 结果
     */
    public int insertMessageRecord(MessageRecord messageRecord);


    public void addMessageRecord(MessageRecord messageRecord);


    /**
     * 修改咨询记录信息
     * 
     * @param messageRecord 咨询记录信息
     * @return 结果
     */
    public int updateMessageRecord(MessageRecord messageRecord);

    /**
     * 批量删除咨询记录信息
     * 
     * @param messageIds 需要删除的咨询记录信息主键集合
     * @return 结果
     */
    public int deleteMessageRecordByMessageIds(String messageIds);

    /**
     * 删除咨询记录信息信息
     * 
     * @param messageId 咨询记录信息主键
     * @return 结果
     */
    public int deleteMessageRecordByMessageId(Long messageId);
}
