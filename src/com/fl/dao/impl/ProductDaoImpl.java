package com.fl.dao.impl;

import com.fl.dao.ProductDao;
import com.fl.entity.Product;
import com.fl.service.ProductService;
import com.fl.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/16:12
 * @Description:
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public long selectCountById(String tid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select count(1) from product where t_id=?";
      Object result=queryRunner.query(sql,new ScalarHandler(),tid);

    long total=(long)result;
        return total;
    }

    @Override
    public List<Product> selectProductByPage(int page, int pageSize, String tid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());


        String sql = "select p_id ,t_id ,p_name ,p_time ," +
                "p_image ,p_state  ,p_info ,p_price  " +
                "from product where t_id = ? limit ?,?;";

        List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),
                tid, (page - 1) * pageSize, pageSize);

        return list;
    }

    @Override
    public Product selectProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select p_id ,t_id ,p_name ,p_time  ," +
                     "p_image ,p_state  ,p_info  ,p_price " +
                     "from product where p_id = ?;";
        Product product= queryRunner.query(sql,new BeanHandler<Product>(Product.class),pid);

        return product;
    }


}
