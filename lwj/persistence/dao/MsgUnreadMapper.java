package com.lwj.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lwj.persistence.pojo.MsgUnread;

public interface MsgUnreadMapper {
    //id 接收者（好友或群） gid 0代表好友 1代表群 fromuser表示发送者
    
    //删除
    int deleteMsg(@Param("id")Integer id,@Param("fromuser")Integer fromuser,@Param("uid")Integer uid);
    //自动插入
    int countMsg(@Param("id")Integer id,@Param("fromuser")Integer fromuser,@Param("uid")Integer uid);
    
    int insertSelective(MsgUnread record);

    int updateByPrimaryKeySelective(MsgUnread record);

    int updateByPrimaryKey(MsgUnread record);
}