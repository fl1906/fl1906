package com.fl.controller;

import com.fl.entity.PageBean;
import com.fl.entity.Product;
import com.fl.service.ProductService;
import com.fl.service.impl.ProductServiceImpl;
import com.fl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/16:09
 * @Description:
 */
@WebServlet("/product")
public class ProductController extends BaseServlet{
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.接受请求参数 tid 类别 id
        String tid= request.getParameter("tid");

        int pageSize=8;
        String currentPage=request.getParameter("currentPage");
        int page=1;
        if (currentPage != null) {
            page=Integer.parseInt(currentPage);
        }

        //2.调用业务逻辑得到前端需要展示的PageBean
        ProductService productService=new ProductServiceImpl();
        PageBean<Product> pageBean=productService.findPage(tid,page,pageSize);
        //3.响应
        request.setAttribute("pageBean",pageBean);
        return Constants.FORWARD+"/goodsList.jsp";
    }

    //商品详情页面
    public String detail(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String pid=request.getParameter("pid");

        ProductService productService=new ProductServiceImpl();
        Product product=productService.findProductById(pid);

        request.setAttribute("product",product);

        return  Constants.FORWARD+"/goodsDetail.jsp";
    }

}
