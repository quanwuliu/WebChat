package com.lwj.persistence.dao;

import java.util.List;

import com.lwj.persistence.pojo.Friend;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer num);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer num);

    List<Friend> selectByUID(Integer uid);
    
    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}