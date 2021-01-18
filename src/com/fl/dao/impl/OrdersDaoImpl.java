package com.fl.dao.impl;

import com.fl.dao.OrdersDao;
import com.fl.entity.Address;
import com.fl.entity.Item;
import com.fl.entity.Orders;
import com.fl.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/17/16:31
 * @Description:
 */
public class OrdersDaoImpl implements OrdersDao {
    @Override
    public void insertOrders(Orders orders) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="insert into orders (o_id,a_id,u_id,o_count,o_state,o_time)"+
                "values(?,?,?,?,?,?);";
        queryRunner.update(sql,orders.getOid(),orders.getAid(),orders.getUid(),orders.getOcount(),orders.getOstate(),orders.getOtime());
    }

    @Override
    public void insertItems(List<Item> items) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        Object params[][]=new Object[items.size()][];

        String sql="insert into item(o_id,p_id,i_count,i_num)value(?,?,?,?);";
        for (int i = 0; i < items.size(); i++) {
            Item item=items.get(i);
            params[i]=new Object[]{item.getOid(),item.getPid(),item.getIcount(),item.getInum()};

        }
        queryRunner.batch(sql,params);
    }



    @Override
    public List<Orders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select trim(o.o_id)oid,truncate(o.u_id,0)uid,truncate(o.a_id,0)aid," +
                "truncate(o.o_count,2)ocount,o.o_time otime,truncate(o.o_state,0)ostate," +
                "trim(a.a_name)aname,trim(a.a_phone)aphone,trim(a.a_detail)adetail, " +
                "truncate(a.a_state,0)astate from address a join orders o on a.a_id = o.a_id " +
                "where o.u_id = ?;";

        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);


        if (list == null) {
            return null;
        }

        List<Orders> ordersList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Orders orders = new Orders();
            Address address = new Address();
           // ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.populate(orders, list.get(i));
            BeanUtils.populate(address, list.get(i));
            orders.setAddress(address);
            ordersList.add(orders);
        }

        return ordersList;
    }
}
