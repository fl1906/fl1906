package com.fl.controller;

import com.fl.entity.User;
import com.fl.service.UserService;
import com.fl.service.impl.UserServiceImpl;
import com.fl.utils.Base64Utils;
import com.fl.utils.Constants;
import com.fl.utils.MD5Utils;
import com.fl.utils.RandomUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/13/15:25
 * @Description:用户模块控制
 */
@WebServlet("/user")
public class UserController extends BaseServlet{
   public String check(HttpServletRequest req,HttpServletResponse resp) throws SQLException {
       //获取用户名,与前端jsp建立联系
    String username=req.getParameter("username");
    if(username==null)
    {
        return Constants.HAS_USER;//不能注册
    }
       UserService userService=new UserServiceImpl();
   boolean b= userService.checkedUser(username);
   if(b)
   {
       return Constants.HAS_USER;//用户存在
   }
   return Constants.NOT_HAS_USER;
   }
   public String register(HttpServletRequest req,HttpServletResponse resp){
       //获取用户信息
    Map<String ,String[]> parameterMap=  req.getParameterMap();
    User user=new User();
       try {
           BeanUtils.populate(user,parameterMap);
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
       //完善用户信息
       user.setUstatus(Constants.USER_NOT_ACTIVE);//0未激活 1已激活
       user.setUrole(Constants.ROLE_CUSTOMER);//普通客户
       user.setCode(RandomUtils.createActive());
       //对密码进行加密
       user.setUpassword(MD5Utils.md5(user.getUpassword()));

       //调用用户的业务逻辑
        UserService userService=new UserServiceImpl();
       try {
           userService.registerUser(user);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
           req.setAttribute("registerMsg","注册失败");
           return Constants.FORWARD+"/register.jsp";
       }
       //响应

       return Constants.FORWARD+"/registerSuccess.jsp";
   }
   //邮箱激活验证
    public String active(HttpServletRequest req,HttpServletResponse resp) throws SQLException {
       //1.获取激活码
       String c= req.getParameter("c");
       String code= Base64Utils.decode(c);
        //2.调用激活的业务逻辑
         UserService userService=new UserServiceImpl();
         int i=userService.activeUser(code);
        //3.响应（3种状态：激活失败（未找到code），已经激活，激活成功）
          if(i==0)
          {
              req.setAttribute("msg","未激活成功");
          }else if(i==1)
          {
              req.setAttribute("msg","激活成功，请登录！");
          }else {
              req.setAttribute("msg","已经激活过了！！");
          }
       return Constants.FORWARD+"/message.jsp";
    }
    public String login(HttpServletRequest request,HttpServletResponse response) throws SQLException {
       //获取请求参数，用户名，密码，验证码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String code=request.getParameter("code");
        String auto=request.getParameter("auto");
        //判断验证码是否正确
        HttpSession session=request.getSession();
        String codeStr=(String)session.getAttribute("code");
        if(code == null||!code.equalsIgnoreCase(codeStr))
        {
            request.setAttribute("msg","验证码错误");
            return Constants.FORWARD+"/login.jsp";
        }
        //判断账号密码是否正确
         UserService userService=new UserServiceImpl();
        User user=userService.login(username,password);

        //响应,如果user==null 证明账号或者密码错误 user!=null 但是账号没有激活也不能登录
        if (user == null) {
            request.setAttribute("msg","账号或者密码错误~~");
            return Constants.FORWARD+"/login.jsp";
        }
       if ((Constants.USER_NOT_ACTIVE).equals(user.getUstatus()))
        {
            request.setAttribute("msg","账号未激活！");
            return Constants.FORWARD +"/login.jsp";
        }
       //判断是否勾选自动登录
        if(auto==null)
        {
            //没有勾选，将本地的cookie清空
            Cookie cookie=new Cookie(Constants.AUTO_NAME,"");
            cookie.setPath("/");
            cookie.setMaxAge(0);//最大存活时间
            response.addCookie(cookie);
        }else{
            //自定义登录数据库存储时间
            String content=username+Constants.FLAG+password;
            content=Base64Utils.encode(content);//暗文密码
            Cookie cookie=new Cookie(Constants.AUTO_NAME,content);//存入cookie
            cookie.setPath("/");
            cookie.setMaxAge(1*24*60*60);
            response.addCookie(cookie);
        }
        session.setAttribute("loginUser",user);
       return Constants.REDIRECT+"/index.jsp";
    }
     //注销登录，清空数据
    public String logOut(HttpServletRequest request,HttpServletResponse response)
    {
        //清空session中的用户数据
            HttpSession session=request.getSession();
            session.removeAttribute("loginUser");
        //清空和覆盖cookie中的自动登录信息
        Cookie cookie=new Cookie(Constants.AUTO_NAME,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //转发到登录页面
        request.setAttribute("msg","注销登录成功！！");
        return Constants.FORWARD+"/login.jsp";
    }

}
