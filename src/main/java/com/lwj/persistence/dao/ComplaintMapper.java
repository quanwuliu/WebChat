package com.lwj.persistence.dao;

import com.lwj.persistence.pojo.Complaint;
import com.lwj.persistence.pojo.ComplaintKey;

public interface ComplaintMapper {
    int deleteByPrimaryKey(ComplaintKey key);

    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(ComplaintKey key);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);
}