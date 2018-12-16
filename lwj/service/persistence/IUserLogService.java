package com.lwj.service.persistence;

import com.lwj.util.enums.UserLogType;
import com.lwj.util.pojo.SocketUser;

public interface IUserLogService {

    public void insertLog(SocketUser user, UserLogType type);
}
