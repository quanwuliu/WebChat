package com.lwj.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.pojo.Admin;
import com.lwj.service.*;


@Controller
@RequestMapping("/basic")
public class BasicController {
	@Resource
	ILoginService loginService;
	
	@RequestMapping("/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id_string  = request.getParameter("id");
		

	}
	
	@RequestMapping("/login2")
	public String login2(HttpServletRequest request,Model model){
		String account  = request.getParameter("account");
		String password  = request.getParameter("password");
		JsonResult result = loginService.login(account, password);
//		int adminId = Integer.parseInt(request.getParameter("id"));
//		Admin admin = this.adminService.selectAdminById(adminId);
		model.addAttribute("result", result);
		return "login2";
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
