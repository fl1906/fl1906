package com.fl.dao;

import com.fl.entity.Item;
import com.fl.entity.Orders;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/17/16:31
 * @Description:
 */
public interface OrdersDao {
    void insertOrders(Orders orders) throws SQLException;

    void insertItems(List<Item> items) throws SQLException;

    List<Orders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

  

    Orders selectOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Item> selectItemsByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;

    void updateStateByOid(String oid) throws SQLException;
}
