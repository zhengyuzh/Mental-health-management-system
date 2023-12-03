package com.mentalhealth.project.system.message_record.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.message_record.domain.MessageRecord;
import com.mentalhealth.project.system.message_record.mapper.MessageRecordMapper;
import com.mentalhealth.project.system.message_record.service.IMessageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 咨询记录信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2021-11-16
 */
@Service("MessageRecordServiceImplService")
public class MessageRecordServiceImpl implements IMessageRecordService 
{
    @Autowired
    private MessageRecordMapper messageRecordMapper;

    /**
     * 查询咨询记录信息
     * 
     * @param messageId 咨询记录信息主键
     * @return 咨询记录信息
     */
    @Override
    public MessageRecord selectMessageRecordByMessageId(Long messageId)
    {
        return messageRecordMapper.selectMessageRecordByMessageId(messageId);
    }

    /**
     * 查询咨询记录信息列表
     * 
     * @param messageRecord 咨询记录信息
     * @return 咨询记录信息
     */
    @Override
    public List<MessageRecord> selectMessageRecordList(MessageRecord messageRecord)
    {
        return messageRecordMapper.selectMessageRecordList(messageRecord);
    }

    /**
     * 新增咨询记录信息
     * 
     * @param messageRecord 咨询记录信息
     * @return 结果
     */
    @Override
    public int insertMessageRecord(MessageRecord messageRecord)
    {
        messageRecord.setCreateTime(DateUtils.getNowDate());
        return messageRecordMapper.insertMessageRecord(messageRecord);
    }

    @Override
    public void addMessageRecord(MessageRecord messageRecord) {

        messageRecordMapper.addMessageRecord(messageRecord);
    }

    /**
     * 修改咨询记录信息
     * 
     * @param messageRecord 咨询记录信息
     * @return 结果
     */
    @Override
    public int updateMessageRecord(MessageRecord messageRecord)
    {
        return messageRecordMapper.updateMessageRecord(messageRecord);
    }

    /**
     * 批量删除咨询记录信息
     * 
     * @param messageIds 需要删除的咨询记录信息主键
     * @return 结果
     */
    @Override
    public int deleteMessageRecordByMessageIds(String messageIds)
    {
        return messageRecordMapper.deleteMessageRecordByMessageIds(Convert.toStrArray(messageIds));
    }

    /**
     * 删除咨询记录信息信息
     * 
     * @param messageId 咨询记录信息主键
     * @return 结果
     */
    @Override
    public int deleteMessageRecordByMessageId(Long messageId)
    {
        return messageRecordMapper.deleteMessageRecordByMessageId(messageId);
    }
}
