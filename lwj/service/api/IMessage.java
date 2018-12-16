package com.lwj.service.api;

import com.lwj.util.pojo.JsonPageResult;
import com.lwj.util.pojo.JsonResult;

/**
* webChat
* @date 2017年1月28日 下午10:00:30
* @author gengzhe.ygz
* 
*/
public interface IMessage {
    //消息盒子内容
    JsonPageResult getMessageBox(int uid,int page);

    
    //获得未处理消息数
    Integer countUnreadMessage(int uid);
}
