package com.lwj.service.persistence;

import java.util.HashMap;

import com.lwj.util.pojo.JsonResult;
import com.lwj.util.enums.ResponseType;

public interface IInitService {
	
	public ResponseType getInfo(Integer uid,HashMap<String, Object> map);
	
	public JsonResult init(Integer uid);
	
}