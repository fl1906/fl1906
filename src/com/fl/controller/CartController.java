package com.fl.controller;

import com.fl.entity.Cart;
import com.fl.entity.User;
import com.fl.service.CartService;
import com.fl.service.impl.CartServiceImpl;
import com.fl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/14/15:11
 * @Description:
 */
@WebServlet("/cart")
public class CartController extends BaseServlet{
    public String create(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
       //判断是否已经登录
        HttpSession session=request.getSession();
      User user= (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg","添加购物之前必须先登录~");
            return Constants.REDIRECT+"/login.jsp";
        }

        //商品id以及用户id
        int uid=user.getUid();
        String pid=request.getParameter("pid");

        CartService cartservice=new CartServiceImpl();
        cartservice.createCart(uid,pid);

        return Constants.FORWARD+"/cartSuccess.jsp";
    }
    public String show(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        //判断是否已经登录
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg","添加购物之前必须先登录~");
            return Constants.REDIRECT+"/login.jsp";
        }
        //获取参数
        int uid = user.getUid();
        //调用业务逻辑继续数据查询
        CartService cartService=new CartServiceImpl();
         List<Cart> list= cartService.findAll(uid);
         request.setAttribute("list",list);

        return Constants.FORWARD+"/cart.jsp";
    }
    public String delete(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        //获取c_id
        String cid=request.getParameter("c_id");
        //调用业务逻辑删除
          CartService cartService=new CartServiceImpl();
          cartService.deleteCartByCid(cid);
        //转发到展示的处理方法中
        return Constants.FORWARD+"/cart?method=show";

    }
    public String update(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        //获取c_id
        String cid=request.getParameter("c_id");
        String price=request.getParameter("price");//商品的单件
        String cnum=request.getParameter("c_num");//修改后的数量
        //调用业务逻辑删除
        CartService cartService=new CartServiceImpl();
        cartService.updateCartByCid(cid,price,cnum);
        //转发到展示的处理方法中
        return Constants.FORWARD+"/cart?method=show";

    }
    public String clear(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String uid=request.getParameter("uid");
        String price=request.getParameter("price");//商品的单件
        String cnum=request.getParameter("c_num");//修改后的数量
        CartService cartService=new CartServiceImpl();
        cartService.clearCart(uid);

        return Constants.FORWARD+"/cart?method=show";
    }

}
