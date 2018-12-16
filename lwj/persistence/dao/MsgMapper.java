package com.lwj.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lwj.persistence.pojo.Msg;

public interface MsgMapper {
    
    List<Msg> selectByUIDTop(@Param("uid")Integer uid,@Param("num")Integer num);
    List<Msg> selectByFUIDTop(@Param("fromuser")Integer fromuser,@Param("uid")Integer uid,@Param("num")Integer num);
    List<Msg> selectByFromIDTop(@Param("fromuser")Integer fromuser,@Param("num")Integer num);
    
    int deleteByPrimaryKey(Integer id);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);
}