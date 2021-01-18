package com.fl.controller;

import com.fl.entity.Address;
import com.fl.entity.User;
import com.fl.service.AddressService;
import com.fl.service.impl.AddressServiceImpl;
import com.fl.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/16/15:47
 * @Description:
 */
@WebServlet("/address")
public class AddressController extends BaseServlet{
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
       HttpSession session=request.getSession();
     User user=(User)session.getAttribute("loginUser");
     if(user==null)
     {
         session.setAttribute("msg","需要先进行登录！！");
         return Constants.REDIRECT+"/login.jsp";
     }
      int uid=user.getUid();
      AddressService addressService=new AddressServiceImpl();
      List<Address> addresses=addressService.findAddressByUid(uid);
      request.setAttribute("list",addresses);


        return Constants.FORWARD+"/self_info.jsp";
    }
    public String add(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
       //获取请求参数
        Map<String,String[]> map= request.getParameterMap();
        Address address=new Address();
        BeanUtils.populate(address,map);

        //调用业务逻辑，进行地址添加
        AddressService addressService=new AddressServiceImpl();
        addressService.saveAddress(address);

        //转发到展示的方法

        return Constants.FORWARD+"/address?method=show";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        String aid = request.getParameter("aid");
        AddressService addressService=new AddressServiceImpl();
        addressService.deleteAddressByAid(aid);
        return Constants.FORWARD+"/address?method=show";
    }
    public String setDefault(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        String aid = request.getParameter("aid");
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("loginUser");
        if(user==null)
        {
            session.setAttribute("msg","需要先进行登录！！");
            return Constants.REDIRECT+"/login.jsp";
        }
        int uid=user.getUid();
        AddressService addressService=new AddressServiceImpl();
        addressService.setAddressToDefault(aid,uid);
        return Constants.FORWARD+"/address?method=show";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
       Address address=new Address();
       BeanUtils.populate(address,map);
        AddressService addressService=new AddressServiceImpl();
        addressService.updateByAid(address);

        return Constants.FORWARD+"/address?method=show";
    }

}
