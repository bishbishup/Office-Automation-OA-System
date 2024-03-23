package com.imooc.oa.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.UserService;
import com.imooc.oa.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        // 接收用户输入
        String  username = req.getParameter("username");
        String  password = req.getParameter("password");
        // 调用业务逻辑
        ResponseUtils response = null;
        try{
            User user = userService.checkLogin(username,password);
            user.setPassword(null);
            user.setSalt(null);
            // 处理结果编码，0代表成功，非0代表失败
            response = new ResponseUtils().put("user",user);
        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());

        }
        // 返回JSON结果
        resp.getWriter().println(response.toJsonString());
    }
}
