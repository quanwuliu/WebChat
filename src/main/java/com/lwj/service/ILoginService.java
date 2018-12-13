package com.lwj.service;

import java.util.HashMap;

import com.lwj.util.pojo.JsonResult;
import com.lwj.util.enums.ResponseType;

public interface ILoginService {
	
	public  ResponseType login_check(int id, String password ,HashMap<String, Object> map);
	
	public JsonResult login(int id, String password);
	
}
