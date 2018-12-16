package com.lwj.util.pojo.message;

import com.lwj.util.enums.ToClientMessageType;


public class ToClientMessageResult {

    public ToClientMessageType getType() {
        return type;
    }

    public void setType(ToClientMessageType type) {
        this.type = type;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    private ToClientMessageType type;
    private Object msg;
}