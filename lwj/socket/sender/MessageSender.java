package com.lwj.socket.sender;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import com.lwj.service.persistence.IMessageService;
import com.lwj.util.enums.ChatType;
import com.lwj.util.enums.ToClientMessageType;
import com.lwj.util.factory.WebChatFactory;
import com.lwj.util.pojo.SocketUser;
import com.lwj.util.pojo.message.ToClientMessageResult;
import com.lwj.util.pojo.message.ToClientTextMessage;
import com.lwj.util.pojo.message.ToServerMessageMine;
import com.lwj.util.pojo.message.ToServerTextMessage;

/**
 * 发送信息类
 * 所有从客户端到服务端的消息转发到此类进行消息处理
 * ToServerTextMessage转换为ToClientTextMessage
 * 如果是单聊，直接从缓存取出对象的session进行消息发送，群聊则需要从缓存中取出该群里所有人的id进行遍历发送消息，
 */
public class MessageSender {
    private IMessageService messageService = (IMessageService) WebChatFactory.beanFactory("messageService");
    //发送信息业务逻辑
    public void sendMessage(ToServerTextMessage message){

        int toUserId = message.getTo().getId();
        //获取发送人
        String sendUserId = Integer.toString(message.getMine().getId());
        String type =  message.getTo().getType();
        //消息生成
        String toClientMessage = getToClientMessage(message);

        System.out.println("当前消息类型是"+type);

        sendMessage(toUserId, toClientMessage,message);
        //最后保存到数据库
        saveMessage(message);

    }
    
  //给单个用户发
    private  void sendMessage(Integer userId,String message,ToServerTextMessage toServerTextMessage){
        SocketUser user = WebChatFactory.createManager().getUser(userId);
        if (user.isExist()) {
            if (user.getSession() == null) {
//                LayIMLog.info("该用户不在线 ");
                saveOfflineMessage(userId,toServerTextMessage);
            } else {
                Session session = user.getSession();
                if (session.isOpen()) {
                    //构造用户需要接收到的消息
                        session.getAsyncRemote().sendText(message);
                }
            }
        }else{
//            LayIMLog.info("该用户不在线 ");
            saveOfflineMessage(userId,toServerTextMessage);
        }
    }
    
  //同步给单个用户发（离线推送）
    public  void sendMessage(Integer userId,String message){
        SocketUser user = WebChatFactory.createManager().getUser(userId);
        if (user.isExist()) {
            if (user.getSession() == null) {
            } else {
                Session session = user.getSession();
                if (session.isOpen()) {
                        try {
                            session.getBasicRemote().sendText(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }else{
        }
    }
    //发送离线消息
    public void sendOfflineMessage(Integer userId){
        //获取好友的离线消息
        List<List<String>> msgList = messageService.getFriendUnreadMessage(userId);
        for (List<String> list : msgList) {
            for (String string : list) {
                sendMessage(userId,string);
            }
        }
    }
    
    public void saveOfflineMessage(Integer recevUid,ToServerTextMessage message){
        if(message==null)  return;
        messageService.offlineMessage(recevUid,message);
    }
    
    //保存到数据库
    private void saveMessage(ToServerTextMessage message){
        
        messageService.insertMessage(message);
        
    }

    //根据客户端发送来的消息，构造发送出去的消息
     /*
        *  username: data.mine.username
            , avatar: data.mine.avatar
            , id: data.mine.id
            , type: data.to.type
            , content:data.mine.content
            , timestamp: new Date().getTime()
        * */
    private String getToClientMessage(ToServerTextMessage message) {

        ToClientTextMessage toClientTextMessage = new ToClientTextMessage();

        ToServerMessageMine mine = message.getMine();

        toClientTextMessage.setUsername(mine.getUsername());
        toClientTextMessage.setAvatar(mine.getAvatar());
        toClientTextMessage.setContent(mine.getContent());
        toClientTextMessage.setType(message.getTo().getType());

        //发送人的id
        toClientTextMessage.setId(mine.getId());
        toClientTextMessage.setTimestamp(new Date().getTime());

        //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        result.setMsg(toClientTextMessage);
        result.setType(ToClientMessageType.TYPE_TEXT_MESSAGE);

        return WebChatFactory.createSerializer().toJSON(result);
    }

    //生成对应的groupId
    private long getGroupId(int sendUserId,int toUserId,String type){

        String sendUserIdStr = Integer.toString(sendUserId);
        String toUserIdStr = Integer.toString(toUserId);
        String groupIdStr = "";
        //按照固定次序生成相应的聊天组
        if (sendUserId > toUserId){
            groupIdStr = sendUserIdStr + toUserIdStr;
        }else{
            groupIdStr = toUserIdStr + sendUserIdStr;
        }
        long groupId = Long.parseLong(groupIdStr);
        return groupId;
    }
}
