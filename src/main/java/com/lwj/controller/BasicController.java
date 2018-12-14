package com.lwj.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.jvm.hotspot.asm.ImmediateOrRegister;

import com.lwj.util.pojo.JsonResult;
import com.lwj.service.*;
import com.lwj.service.impl.RegisterService;


@Controller
@RequestMapping("/basic")
public class BasicController {
	@Resource
	ILoginService loginService;
	
	@Resource
	IInitService initService;

	@Resource
	IRegisterService registerService;
	
	@RequestMapping("/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account  = request.getParameter("account");
		String password  = request.getParameter("password");
		String tel  = request.getParameter("tel");
		JsonResult result = registerService.register(account, password,tel);
		sendResult(response,result);
	}
	
	@RequestMapping("/login2")
	public String login2(HttpServletRequest request,Model model){
		String account  = request.getParameter("account");
		String password  = request.getParameter("password");
		JsonResult result = loginService.login(account, password);
		model.addAttribute("result", result);
		return "login2";
	}
	
	@RequestMapping("/info")
	public String info(HttpServletRequest request,Model model){
		int uid = Integer.parseInt(request.getParameter("uid"));
		JsonResult result = initService.init(uid);
		model.addAttribute("result", result);
		return "info";
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account  = request.getParameter("account");
//		Integer account = null;
//		if(account_string!=null)
//			account = Integer.valueOf(account_string);
//			
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
