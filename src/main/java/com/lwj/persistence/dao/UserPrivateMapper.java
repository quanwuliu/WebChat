package com.lwj.persistence.dao;

import com.lwj.persistence.pojo.UserPrivate;

public interface UserPrivateMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserPrivate record);

    int insertSelective(UserPrivate record);

    UserPrivate selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserPrivate record);

    int updateByPrimaryKey(UserPrivate record);
}