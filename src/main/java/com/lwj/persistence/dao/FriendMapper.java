package com.lwj.persistence.dao;

import com.lwj.persistence.pojo.FriendKey;

public interface FriendMapper {
    int deleteByPrimaryKey(FriendKey key);

    int insert(FriendKey record);

    int insertSelective(FriendKey record);
}