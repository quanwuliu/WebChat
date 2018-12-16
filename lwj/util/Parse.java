package com.lwj.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.lwj.persistence.pojo.Msg;
import com.lwj.persistence.pojo.UserPublic;
import com.lwj.util.enums.ChatType;
import com.lwj.util.enums.ToClientMessageType;
import com.lwj.util.factory.WebChatFactory;
import com.lwj.util.pojo.message.ToClientMessageResult;
import com.lwj.util.pojo.message.ToClientTextMessage;

public class Parse {
    
    public static String getStringToClientMessage(UserPublic sender,Msg message) {

        ToClientTextMessage toClientTextMessage = new ToClientTextMessage();

        toClientTextMessage.setUsername(sender.getNickname());
        //toClientTextMessage.setAvatar(Global.URL+sender.getAvatar());
        toClientTextMessage.setContent(message.getMsg());
        toClientTextMessage.setType(ChatType.FRINED.getName());
        if (toClientTextMessage.getType().equals(ChatType.GROUP.getName())) {
            toClientTextMessage.setId(message.getGid());
        } else {
            toClientTextMessage.setId(message.getFromuser());
        }
        toClientTextMessage.setTimestamp(message.getAddtime().getTime());

        //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        result.setMsg(toClientTextMessage);
        result.setType(ToClientMessageType.TYPE_TEXT_MESSAGE);

        return WebChatFactory.createSerializer().toJSON(result);
    }
    
    public static List<String> getStringListToClientMessage(UserPublic sender,List<Msg> message) {
        List<String> resultlist = new ArrayList<String>();
        for(int i = message.size()-1;i>=0;i--)
            resultlist.add(getStringToClientMessage(sender, message.get(i)));
        return resultlist;
    }
    
    public static String getResultToString(ToClientMessageType type,Object message){
      //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        result.setMsg(message);
        result.setType(type);
        return WebChatFactory.createSerializer().toJSON(result);
    }
    
    public static String showTime(Date date){
        long dataTime = date.getTime();
        Calendar calendar =  Calendar.getInstance();
        calendar.setTimeInMillis(dataTime);
        long now=System.currentTimeMillis();//当前时间毫秒数 
        long today=now/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        if(dataTime<today-1000*60*60*24*30l){  
            return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"";  
        }  
        if(dataTime<today-1000*60*60*24*2l){  
            return (calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"";  
        }  
        if(dataTime<today-1000*60*60*24*1l){  
            return "前天 "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"";  
        }  
        if(dataTime<today){  
            return "昨天 "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"";  
        }
        if(dataTime>now-1000*60l){  
            return "刚刚";  
        }  
        if(dataTime>now-1000*60*2l){  
            return "一分钟前";  
        }  
        if(dataTime>now-1000*60*3l){  
            return "两分钟前";  
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }  
    public static void main(String args[]){
    }
}
