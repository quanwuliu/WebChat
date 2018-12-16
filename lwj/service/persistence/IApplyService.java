package com.lwj.service.persistence;

import java.util.List;

import com.lwj.persistence.pojo.Apply;

public interface IApplyService {
    //增加
    void insertApply(int fromuser,int fromuser_fid,int uid,String msg) throws Exception;
    
    //删除
    void deleteApply(int fromuser,int uid);
    
    //根据目标人查找
    List<Apply> selectApplyByUid(int uid,int offset);
    
    //根据目标人查找数量
    int countApplyByUid(int uid);
    
    //验证是否存在
    boolean isExist(int fromuser,int uid);
    
}
