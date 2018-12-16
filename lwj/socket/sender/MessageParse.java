package com.lwj.socket.sender;

import java.util.HashMap;

import com.lwj.util.enums.OnlineStatus;
import com.lwj.util.enums.ToClientMessageType;
import com.lwj.util.factory.WebChatFactory;
import com.lwj.util.pojo.message.ToClientMessageResult;


public class MessageParse {
    public static String ServiceOnlineStatus(Integer uid , OnlineStatus status){
      //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        HashMap<String, Object> msg = new HashMap<String, Object>();
        msg.put("id", uid);
        msg.put("status", status.getName());
        result.setMsg(msg);
        result.setType(ToClientMessageType.SERVICE_ONLINE_STATUS);

        return WebChatFactory.createSerializer().toJSON(result);
    }
}
