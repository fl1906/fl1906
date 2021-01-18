package com.fl.service;

import com.fl.entity.PageBean;
import com.fl.entity.Product;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/16:10
 * @Description:
 */
public interface ProductService {
    PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException;

    Product findProductById(String pid) throws SQLException;
}
