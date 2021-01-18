package com.fl.service.impl;

import com.fl.dao.ProductDao;
import com.fl.dao.impl.ProductDaoImpl;
import com.fl.entity.PageBean;
import com.fl.entity.Product;
import com.fl.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/16:11
 * @Description:
 */
public class ProductServiceImpl implements ProductService {
    @Override
    public PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException {
        ProductDao productDao=new ProductDaoImpl();
        long count= productDao.selectCountById(tid);//查询总条数
        List<Product> list=productDao.selectProductByPage(page,pageSize,tid);//查询当前页对应的商品

        return new PageBean<Product>(list,page,pageSize,count);
    }

    @Override
    public Product findProductById(String pid) throws SQLException {
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.selectProductByPid(pid);

        return product;
    }
}
