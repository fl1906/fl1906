package com.fl.filter;

import com.fl.entity.User;
import com.fl.service.UserService;
import com.fl.service.impl.UserServiceImpl;
import com.fl.utils.Base64Utils;
import com.fl.utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/17/14:59
 * @Description:
 */
//@WebServlet("/login.jsp")//过滤器只会过滤正常请求和重定向，不会过滤请求转发
public class AutoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        Cookie[] cookies=request.getCookies();
        if (cookies != null) {
            //本地存储了cookie
            String content=null;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(Constants.AUTO_NAME)){
                    content=cookie.getValue();
                }
            }
            if(content!=null)
            {
                //读取到了cookie
                content= Base64Utils.decode(content);
              String[] split=  content.split(Constants.FLAG);
              String username=split[0];
              String password=split[1];

                UserService userService=new UserServiceImpl();
                try {
                    User user=userService.login(username,password);
                    if (user != null) {
                        //可以自动登录
                    HttpSession session= request.getSession();
                    session.setAttribute("loginUser",user);
                 HttpServletResponse response= (HttpServletResponse) servletResponse;
                 response.sendRedirect(request.getContextPath()+"/index.jsp");
                    }else {
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }else{
                //没有读取到我们想要的cookie
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else{
            //本地没有cookie
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
