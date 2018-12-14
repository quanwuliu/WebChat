package com.lwj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.AdminMapper;
import com.lwj.persistence.pojo.Admin;
import com.lwj.service.IAdminService;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	@Resource
	private AdminMapper adminDao;
	
	@Override
	public Admin selectAdminById(Integer aid) {
		// TODO Auto-generated method stub
		return this.adminDao.selectByPrimaryKey(aid);
	}
	
	@Override
	public int insert(Admin record) {
		// TODO Auto-generated method stub
		return this.adminDao.insert(record);
	}
}