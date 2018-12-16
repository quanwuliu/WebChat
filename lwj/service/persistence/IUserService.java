package com.lwj.service.persistence;

import java.util.List;

import com.lwj.persistence.pojo.UserPublic;
public interface IUserService {

    public UserPublic getUserById(int userId);
    
    public UserPublic getUserByUID(int userUID);
    
    public String insertUser(Integer Uid,String name,String photo,String sign);
    
    public List<UserPublic> searchUsersByKeyword(String keyword);
}
