package com.lwj.service.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwj.persistence.pojo.Apply;
import com.lwj.persistence.pojo.MessageBox;

import com.lwj.persistence.pojo.UserPublic;
import com.lwj.service.api.IMessage;
import com.lwj.service.persistence.IApplyService;
import com.lwj.service.persistence.IMessageboxService;
import com.lwj.service.persistence.IUserService;
import com.lwj.util.Parse;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonPageResult;
import com.lwj.util.pojo.JsonResult;


@Service
public class Message implements IMessage {
    @Autowired
    IApplyService applyService;
    @Autowired
    IMessageboxService messageService;
    @Autowired
    IUserService userService;
    
    @Override
    public JsonPageResult getMessageBox(int uid, int page) {
        final int pagesize = 5;
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
        int applyCount = applyService.countApplyByUid(uid);
        int msgCount = messageService.countMessageboxByUID(uid);
        int pages = (int) Math.ceil( (applyCount+msgCount)/Double.valueOf(pagesize) );
        //获得偏移量
        int offset = (page-1)*pagesize;
        //获得申请列表
        if(offset + pagesize <= applyCount)//全是申请列表
        {
            List<Apply> applyList = applyService.selectApplyByUid(uid, offset);
            for (Apply apply : applyList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", 0);
                map.put("content", "添加你为好友");
                map.put("uid", uid);
                map.put("from", apply.getFromuser());
                map.put("remark", apply.getMsg());
                map.put("time", Parse.showTime(apply.getAddtime()));
                UserPublic user = userService.getUserByUID(apply.getFromuser());
                HashMap<String, Object> userMap = new HashMap<String, Object>();
                userMap.put("id", user.getUid());
                //userMap.put("avatar", Global.URL+user.getHeadphoto());
                userMap.put("username", user.getNickname());         
                map.put("user", userMap);
                data.add(map);
            }
        }else if(offset <= applyCount){ //申请列表最后一页+历史第一页
            int count = 0;
            List<Apply> applyList = applyService.selectApplyByUid(uid, offset);
            for (Apply apply : applyList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", 0);
                map.put("content", "添加你为好友");
                map.put("uid", uid);
                map.put("remark", apply.getMsg());
                map.put("time", Parse.showTime(apply.getAddtime()));
                UserPublic user = userService.getUserByUID(apply.getFromuser());
                HashMap<String, Object> userMap = new HashMap<String, Object>();
                userMap.put("id", user.getUid());
                //userMap.put("avatar", Global.URL+user.getHeadphoto());
                userMap.put("username", user.getNickname());
                //userMap.put("sign", user.getSign());
                map.put("user", userMap);
                data.add(map);
                count++;
            }
            List<MessageBox> msgList = messageService.selectMessagebox(uid,0);
            for (MessageBox messageBox : msgList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", messageBox.getId());
                map.put("content", messageBox.getContent());
                map.put("uid", messageBox.getUid());
                map.put("time", Parse.showTime(messageBox.getAddtime()));
                data.add(map);
                if(++count == 5) break;
            }
        }else{//获得历史消息盒子
            offset -=applyCount;
            List<MessageBox> msgList = messageService.selectMessagebox(uid,offset);
            for (MessageBox messageBox : msgList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", messageBox.getId());
                map.put("content", messageBox.getContent());
                map.put("uid", messageBox.getUid());
                map.put("time", Parse.showTime(messageBox.getAddtime()));
                data.add(map);
            }
        }
        
        JsonPageResult result = new JsonPageResult(ResponseType.SUCCESS,pages,data);
        return result;
    }


    @Override
    public Integer countUnreadMessage(int uid) {
//        return messageService.countMessageboxByUID(uid);
        return applyService.countApplyByUid(uid);
    }

}
