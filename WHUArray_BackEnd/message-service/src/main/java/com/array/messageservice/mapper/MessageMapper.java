package com.array.messageservice.mapper;

import com.array.commonmodule.bean.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yee
 */
@Mapper
public interface MessageMapper {
    Long addMessage(@Param("message")Message message, @Param("userIds") List<Long> userIds);

    int deleteMessage(Long messageId);

    Message getMessageById(Long messageId);

    List<Message> getMessageByUserId(Long userId);

    int addMessageToUser(@Param("messageId") Long messageId, @Param("userIds") List<Long> userIds);
}
