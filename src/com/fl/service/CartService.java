package com.fl.service;

import com.fl.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/14/15:14
 * @Description:
 */
public interface CartService {
    void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Cart> findAll(int uid) throws IllegalAccessException, SQLException, InvocationTargetException;

    void deleteCartByCid(String cid) throws SQLException;


    void updateCartByCid(String cid, String price, String cnum) throws SQLException;

    void clearCart(String uid) throws SQLException;
}
