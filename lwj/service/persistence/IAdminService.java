package com.lwj.service.persistence;

import com.lwj.persistence.pojo.Admin;

public interface IAdminService {
	Admin selectAdminById(Integer aid);
	
	int insert(Admin record);
}