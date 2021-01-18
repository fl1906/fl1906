package com.fl.service.impl;

import com.fl.dao.CartDao;
import com.fl.dao.OrdersDao;
import com.fl.dao.impl.CartDaoImpl;
import com.fl.dao.impl.OrdersDaoImpl;
import com.fl.entity.Cart;
import com.fl.entity.Item;
import com.fl.entity.Orders;
import com.fl.service.OrdersService;
import com.fl.utils.RandomUtils;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/17/16:31
 * @Description:
 */
public class OrdersServiceImpl implements OrdersService {
    @Override
    public void createOrder(String aid, String uid, String sum) throws IllegalAccessException, SQLException, InvocationTargetException {
        //创建一个订单项保存
        Orders orders = new Orders();
        String orderId = RandomUtils.createOrderId();

        orders.setOid(orderId);
        orders.setAid(Integer.parseInt(aid));
        orders.setUid(Integer.parseInt(uid));
        orders.setOtime(new Date());
        orders.setOcount(new BigDecimal(sum));
        orders.setOstate(1);//未支付状态


        //保存订单
        OrdersDao ordersDao=new OrdersDaoImpl();
        ordersDao.insertOrders(orders);

        //将购物车转成订单项
        CartDao cartDao=new CartDaoImpl();
      List<Cart> carts= cartDao.selectCartsByUid(Integer.parseInt(uid));
        List<Item> items=new ArrayList<>();
        for (Cart cart : carts) {
           Item item=new Item();
           item.setOid(orderId);
           item.setPid(cart.getP_id());
           item.setInum(cart.getC_num());
           item.setIcount(cart.getC_count());
           items.add(item);
        }

        //保存订单对应的订单项
        ordersDao.insertItems(items);

        //清空购物车
        cartDao.deleteCartByUid(uid);
    }

    @Override
    public List<Orders> findOrdersByUid(int uid) throws IllegalAccessException, SQLException, InvocationTargetException {
        OrdersDao ordersDao=new OrdersDaoImpl();
      List<Orders> list=ordersDao.selectOrdersByUid(uid);
        return list;
    }

    @Override
    public Orders findOrdersByOid(String oid) throws IllegalAccessException, SQLException, InvocationTargetException {
        //根据oid查询订单和订单地址信息
          OrdersDao ordersDao=new OrdersDaoImpl();
       Orders orders= ordersDao.selectOrderByOid(oid);
        //根据oid对应的订单项和商品信息
      List<Item> items =ordersDao.selectItemsByOid(oid);
        //订单项集合设置给订单对象
     orders.setItems(items);
        return orders;
    }

    @Override
    public void updateStateByOid(String oid) throws SQLException {
         OrdersDao ordersDao=new OrdersDaoImpl();
         ordersDao.updateStateByOid(oid);
    }
}
