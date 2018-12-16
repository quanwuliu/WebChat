package com.lwj.service.persistence;

import java.util.List;

import com.lwj.util.pojo.message.ToServerTextMessage;

public interface IMessageService {

    // 保存消息
    void insertMessage(ToServerTextMessage message);

 // 根据接收好友id读取
    List<String> getMessageByUIDTop(int uid, int num);
    
 // 根据接收好友id 和发送好友id 读取
    List<String> getMessageByUIDTop(int fromuser,int uid, int num);

    // 根据发送人id读取
    List<String> getMessageByFromIDTop(int fromuser, int num);
    
    // 插入未读信息数据
    void offlineMessage(Integer recvUid,ToServerTextMessage message);

    // 获取未读好友信息
    List<List<String>> getFriendUnreadMessage(Integer id);


}
