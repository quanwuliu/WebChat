package com.lwj.service.persistence;

import java.util.List;

import com.lwj.persistence.pojo.MessageBox;

public interface IMessageboxService {
    //插入一条消息
    void insertMessagebox(int uid,String content);
    
    //根据UID获得消息数量
    int countMessageboxByUID(int uid);
    
    //根据UID获取消息
    List<MessageBox> selectMessagebox(int uid,int offset);
}
