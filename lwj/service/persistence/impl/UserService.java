package com.lwj.service.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.UserPublicMapper;
import com.lwj.persistence.pojo.UserPublic;
import com.lwj.service.persistence.IUserService;
//import com.lwj.util.security.Security; 用户


@Service("userService")
public class UserService implements IUserService {

    @Resource
    private UserPublicMapper userDao;

    @Override
    public UserPublic getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }

    @Override
    public UserPublic getUserByUID(int userUID) {
        return this.userDao.selectByUID(userUID);
    }
    
    @Override
    public String insertUser(Integer uid, String name, String photo, String sign) {
        UserPublic u = getUserByUID(uid);
        if(u!=null && u.getUid().equals(uid))
            return null;
        UserPublic user = new  UserPublic();
        //user.setHeadphoto(photo);
        user.setNickname(name);
        user.setOnline(0);
        user.setUid(uid);
        //user.setPassword(Security.getPassword(uid));用户
        this.userDao.insert(user);
        return null;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List< UserPublic> searchUsersByKeyword(String keyword) {
        if(keyword==null || keyword.equals(""))
            return new ArrayList<UserPublic>();
        return (List<UserPublic>) userDao.searchUserByKeyword(keyword);
    }

}