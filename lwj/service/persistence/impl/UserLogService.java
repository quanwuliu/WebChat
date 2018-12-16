package com.lwj.service.persistence.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.UserPublicMapper;
import com.lwj.persistence.pojo. UserPublic;
import com.lwj.service.persistence.IUserLogService;
import com.lwj.util.enums.UserLogType;
import com.lwj.util.pojo.SocketUser;

/**
 * @date 2017年1月23日 下午7:27:20
 * @author yangengzhe
 *
 */
@Service
public class UserLogService implements IUserLogService {
    
    @Resource
    private UserPublicMapper userLogDao;
    
    @Override
    public void insertLog(SocketUser user, UserLogType type) {
        UserPublic userlog = new UserPublic();
        String ipaddr=(String) user.getSession().getUserProperties().get("ClientIP");
        userlog.setIp(ipaddr);
        userlog.setLog(type.getValue());
        userlog.setUid(user.getUserId());
        userLogDao.insert(userlog);
    }

}
