package com.fl.controller;

import com.fl.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/12/23:29
 * @Description:
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String methodStr= req.getParameter(Constants.TAG);
        //标识符异常处理,跳转到首页
        if(methodStr==null||methodStr.equals(""))
        {
            methodStr=Constants.INDEX;
        }
        //反射
        Class clzz=this.getClass();
        try {
            Method method1=clzz.getMethod(methodStr,HttpServletRequest.class,HttpServletResponse.class);
            Object result= method1.invoke(this,req,resp);
            //处理返回值响应
            if (result != null) {
                String str=(String)result;
                if(str.startsWith(Constants.FORWARD)){
                    String path=str.substring(str.indexOf(Constants.FLAG)+1);
                    req.getRequestDispatcher(path).forward(req,resp);
                }else if(str.startsWith(Constants.REDIRECT)){
                    String path=str.substring(str.indexOf(Constants.FLAG)+1);
                    resp.sendRedirect(path);
                }else  {
                    resp.getWriter().println(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
           //异常统一处理的地方
            req.setAttribute("msg","程序异常了哟~~");
            resp.sendRedirect("/msg.jsp");
        }
    }
    public String index(HttpServletRequest req,HttpServletResponse resp){
       return Constants.FORWARD+"/index.jsp";
    }
    }
