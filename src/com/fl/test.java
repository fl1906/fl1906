package com.fl;

import com.fl.dao.CartDao;
import com.fl.dao.OrdersDao;
import com.fl.dao.impl.CartDaoImpl;
import com.fl.dao.impl.OrdersDaoImpl;
import com.fl.entity.Cart;
import com.fl.entity.Orders;
import com.fl.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/14/23:17
 * @Description:
 */
public class test {
    public static void main(String[] args) throws IllegalAccessException, SQLException, InvocationTargetException {
        OrdersDao ordersDao=new OrdersDaoImpl();
       ordersDao.selectItemsByOid("20210118011038829");


    }
}
