package com.lwj.service.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.MsgMapper;
import com.lwj.persistence.dao.MsgUnreadMapper;
import com.lwj.persistence.dao.UserPublicMapper;
import com.lwj.persistence.pojo.Msg;
import com.lwj.persistence.pojo.MsgUnread;
import com.lwj.persistence.pojo.UserPublic;
import com.lwj.service.persistence.IMessageService;
import com.lwj.util.Parse;
import com.lwj.util.enums.ChatType;
import com.lwj.util.pojo.message.ToServerTextMessage;

@Service
public class MessageService implements IMessageService {

    @Autowired
    MsgMapper msgDao;
    @Autowired
    UserPublicMapper userDao;
    @Autowired
    MsgUnreadMapper msgUnreadDao;
    
    @Override
    public void insertMessage(ToServerTextMessage message) {
        Msg msg = new Msg();
        msg.setAddtime(new Date());
        msg.setFromuser(message.getMine().getId());
        msg.setMsg(message.getMine().getContent());
        if(message.getTo().getType().equals(ChatType.FRINED.getName())){
            msg.setUid(message.getTo().getId());
        }else{
            msg.setGid(message.getTo().getId());
        }
        msgDao.insert(msg);
    }

    @Override
    public List<String> getMessageByUIDTop(int uid, int num) {
        List<Msg> msgList =  msgDao.selectByUIDTop(uid, num);
        UserPublic sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }
    
    @Override
    public List<String> getMessageByUIDTop(int fromuser,int uid, int num) {
        List<Msg> msgList =  msgDao.selectByFUIDTop(fromuser, uid, num);
        UserPublic sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }

    @Override
    public List<String> getMessageByFromIDTop(int fromuser, int num) {
        List<Msg> msgList = msgDao.selectByFromIDTop(fromuser, num);
        UserPublic sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }

    @Override
    public void offlineMessage(Integer recvUid,ToServerTextMessage message) {
        Integer id = message.getTo().getId();
        Integer gid = message.getTo().getType().equals(ChatType.FRINED.getName()) ? 0:1;
        Integer fromuser = gid.equals(1)? 0 : message.getMine().getId(); //如果是组 则发出者为0
        msgUnreadDao.countMsg(id, gid, fromuser,recvUid);
    }

    @Override
    public List<List<String>> getFriendUnreadMessage(Integer id) {
         List<MsgUnread> list= msgUnreadDao.selectByGidId(ChatType.FRINED.getOrdinal(), id, id);
         List<List<String>> result = new ArrayList<List<String>>();
         for (MsgUnread msgUnread : list) {
            Integer fromuser = msgUnread.getFromuser();
            Integer uid = msgUnread.getId();
            Integer num = msgUnread.getMsgcount();
            List<String> result_list = getMessageByUIDTop(fromuser, uid, num);
            msgUnreadDao.deleteMsg(uid, ChatType.FRINED.getOrdinal(), fromuser,uid);
            result.add(result_list);
        }
         return result;
    }


}
