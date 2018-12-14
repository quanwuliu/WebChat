package com.lwj.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.service.IInitService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.dao.UserPublicMapper;
import com.lwj.persistence.pojo.UserPublic;
import com.lwj.persistence.dao.FriendMapper;
import com.lwj.persistence.pojo.Friend;


@Service
public class InitServiceImpl implements IInitService {

	@Resource
	private UserPublicMapper userPublicDao;
	
	@Resource
	private FriendMapper friendDao;
	
	@Override
	public ResponseType getInfo(Integer uid, HashMap<String, Object> map) {
		UserPublic userInfo = userPublicDao.selectByPrimaryKey(uid);
//		FriendKey key = new FriendKey();
//		key.setUid1(uid);
		List<Friend> friendInfo = friendDao.selectByUID(uid);
		map.put("userInfo", userInfo);
		map.put("friendInfo", friendInfo);
		return ResponseType.INFO_GET;
	}

	@Override
	public JsonResult init(Integer uid) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = getInfo(uid,data);
        
        return new JsonResult(responseType,data);
	}

	
}