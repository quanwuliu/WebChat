package com.lwj.controller;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.lwj.persistence.pojo.Admin;
import com.lwj.service.persistence.IAdminService;
 
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private IAdminService adminService;
	
	@RequestMapping("/showAdmin")
	public String toIndex(HttpServletRequest request,Model model){
		int adminId = Integer.parseInt(request.getParameter("id"));
		Admin admin = this.adminService.selectAdminById(adminId);
		model.addAttribute("admin", admin);
		return "showAdmin";
	}
	
	@RequestMapping("/insertAdmin")
	public String insertA(HttpServletRequest request,Model model){
		int aid = Integer.parseInt(request.getParameter("id"));
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		int priority = Integer.parseInt(request.getParameter("priority"));
		
		Admin record = new Admin();
		record.setAid(aid);
		record.setAccount(account);
		record.setPassword(password);
		record.setPriority(priority);
		int success = this.adminService.insert(record);
		model.addAttribute("success", success);
		
		return "insertAdmin";
	}
}
