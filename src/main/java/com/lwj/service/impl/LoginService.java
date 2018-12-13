package com.lwj.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.annotation.Resource;

import com.lwj.service.ILoginService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.dao.UserPrivateMapper;
import com.lwj.persistence.pojo.UserPrivate;

@Service
public class LoginService implements ILoginService{
	
	@Resource
	private UserPrivateMapper userPrivate_Dao;
	
	@Override
	public  ResponseType login_check(int account, String password, HashMap<String, Object> map) {
		UserPrivate user_log = userPrivate_Dao.selectByPrimaryKey(account);
		if(password.equals(user_log.getPassword()))
		{
			map.put("uid", user_log.getUid());
			return ResponseType.LOGIN_SUCESS;
		}
		else
			return ResponseType.LOGIN_WRONG;
	}
	
	@Override
	public JsonResult login(int account, String password) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = login_check(account,password,data);
        
        return new JsonResult(responseType,data);
	}
	
	
	
}
