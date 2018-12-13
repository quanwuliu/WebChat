package com.lwj.util.enums;

public enum ResponseType {
	LOGIN_SUCESS(0,"成功"),
	LOGIN_WRONG(11,"用户名或密码错误");
	
	String msg;
	Integer code;
	
	private ResponseType(Integer code,String msg){
        this.msg = msg;
        this.code = code;
    }
	
	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
  
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
	
}
