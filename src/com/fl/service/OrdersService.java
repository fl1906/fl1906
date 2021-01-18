package com.fl.service;

import com.fl.entity.Orders;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/17/16:30
 * @Description:
 */
public interface OrdersService {
    void createOrder(String aid, String uid, String sum) throws IllegalAccessException, SQLException, InvocationTargetException;

    List<Orders> findOrdersByUid(int uid) throws IllegalAccessException, SQLException, InvocationTargetException;
}
