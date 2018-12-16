package com.lwj.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwj.service.api.IInformation;
import com.lwj.util.factory.WebChatFactory;
import com.lwj.util.pojo.JsonResult;
import com.lwj.util.serializer.IJsonSerializer;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IInformation information;

    @RequestMapping("/init")
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id  = request.getParameter("id");
        String key  = request.getParameter("key");
        JsonResult result = information.init(Integer.valueOf(id), key);
        sendResult(response,result);
    }
    
    @RequestMapping("/searchUser")
    public void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword  = request.getParameter("keyword");
        keyword =new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        JsonResult result = information.searchUserByKeyword(keyword);
        sendResult(response,result);
    }
    
    public void sendResult(HttpServletResponse response,JsonResult result) throws IOException{
        response.setCharacterEncoding("UTF-8");
        IJsonSerializer serializer = WebChatFactory.createSerializer();
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域
        response.getWriter().write(serializer.toJSON(result));
    }
}