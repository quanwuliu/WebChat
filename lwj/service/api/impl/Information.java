package com.lwj.service.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lwj.persistence.pojo.UserPublic;
import com.lwj.service.api.IInformation;
import com.lwj.service.persistence.IUserService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
@Service
public class Information implements IInformation {
    
    @Autowired
    private IUserService userService;



    @Override
    public void addUser(Integer uid, String name, String photo, String sign) {
        userService.insertUser(uid,name,photo,sign);
    }



    @Override
    public UserPublic findUserByUid(Integer uid) {
        return userService.getUserByUID(uid);
    }



    @Override
    public JsonResult searchUserByKeyword(String keyword) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        List<UserPublic> users = userService.searchUsersByKeyword(keyword);
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
        for (UserPublic user : users) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("username", user.getNickname());
            map.put("id", user.getUid());
            //map.put("avatar", Global.URL+user.getAvatar());
            map.put("status", "online");
            list.add(map);
        }
        data.put("list", list);
        return new JsonResult(ResponseType.SUCCESS,data);
    }

    

}
