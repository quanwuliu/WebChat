package com.lwj.service.api;

import java.util.HashMap;

import com.lwj.persistence.pojo.UserPublic;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;

/**
 * @date 2017年1月23日 下午10:47:37
 * @author yangengzhe
 */
public interface IInformation {
    
    public void addUser(Integer uid,String name,String photo,String sign);
    
    public UserPublic findUserByUid(Integer uid);
    
    //查找好友
    public JsonResult searchUserByKeyword(String Keyword);
}
