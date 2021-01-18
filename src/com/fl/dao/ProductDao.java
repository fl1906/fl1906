package com.fl.dao;

import com.fl.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/16:11
 * @Description:
 */
public interface ProductDao {
    long selectCountById(String tid) throws SQLException;

    List<Product> selectProductByPage(int page, int pageSize, String tid) throws SQLException;

    Product selectProductByPid(String pid) throws SQLException;
}
