package com.mentalhealth.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentalhealth.project.system.message_record.domain.ResultMessage;

/**
 * 用来封装消息的工具类
 */
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage, String fromName, Object message){
        try{
            ResultMessage result= new ResultMessage();
            result.setIsSystem(isSystemMessage);
            result.setMessage(message);
            if (fromName != null){
                result.setFromName(fromName);
            }
            ObjectMapper mapper = new ObjectMapper();
            // writeValueAsString 转换成json格式的 try 抛出异常
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
