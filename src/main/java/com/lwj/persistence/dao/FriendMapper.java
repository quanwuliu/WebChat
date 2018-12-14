package com.lwj.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lwj.persistence.pojo.Friend;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer num);
    
    int deleteByUID(@Param(value="uid1")Integer uid1, @Param(value="uid2")Integer uid2);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer num);

    List<Friend> selectByUID(Integer uid);
    
    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}