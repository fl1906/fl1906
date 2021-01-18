package com.fl.controller;

import com.fl.entity.Type;
import com.fl.service.TypeService;
import com.fl.service.impl.TypeServiceImpl;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/11:31
 * @Description:
 */
@WebServlet("/type")
public class TypeController extends BaseServlet{
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        TypeService typeService=new TypeServiceImpl();
        List<Type> types=typeService.findAll();

        Gson gson=new Gson();
        String json= gson.toJson(types);


           return json;
    }
}
