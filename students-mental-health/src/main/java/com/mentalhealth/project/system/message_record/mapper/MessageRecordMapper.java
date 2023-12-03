package com.mentalhealth.project.system.message_record.mapper;

import com.mentalhealth.project.system.message_record.domain.MessageRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 咨询记录信息Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2021-11-16
 */
@Mapper
public interface MessageRecordMapper 
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


    @Insert({"INSERT INTO sys_message_record(message_id, user_id, to_user_id, message_type,message_content,create_time)  VALUES (#{messageId}, #{userId}, #{toUserId}, #{messageType}, #{messageContent}, #{createTime})"})
    int addMessageRecord(MessageRecord messageRecord);

    /**
     * 修改咨询记录信息
     * 
     * @param messageRecord 咨询记录信息
     * @return 结果
     */
    public int updateMessageRecord(MessageRecord messageRecord);

    /**
     * 删除咨询记录信息
     * 
     * @param messageId 咨询记录信息主键
     * @return 结果
     */
    public int deleteMessageRecordByMessageId(Long messageId);

    /**
     * 批量删除咨询记录信息
     * 
     * @param messageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMessageRecordByMessageIds(String[] messageIds);
}
