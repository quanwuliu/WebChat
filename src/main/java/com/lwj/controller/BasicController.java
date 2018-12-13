package com.lwj.controller;



import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwj.util.pojo.JsonResult;


@Controller
@RequestMapping("/basic")
public class BasicController {
	
	@RequestMapping("/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id_string  = request.getParameter("id");
		

	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account_string  = request.getParameter("account");
		Integer account = null;
		if(account_string!=null)
			account = Integer.valueOf(account_string);
			
		String password  = request.getParameter("password");
        
		JsonResult result = loginService.login(account, password); 
		
		sendResult(response,result);
	}
	
	@RequestMapping("/init")
	public void init(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	}
	
	@RequestMapping("/deleteFriend")
	public void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	}
	
	public void sendResult(HttpServletResponse response,JsonResult result) throws IOException{
		response.setCharacterEncoding("UTF-8");
        //IJsonSerializer serializer = WebChatFactory.createSerializer();
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域
        //response.getWriter().write(serializer.toJSON(result));
	}
	

}