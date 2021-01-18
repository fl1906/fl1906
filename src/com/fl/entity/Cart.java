package com.fl.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 数据库对应的购物车表
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private int c_id;
    private int u_id;
    private int p_id;
    private Product product;
    private int c_num = 0;  //购车商品数量
    private BigDecimal c_count; //购物车小计



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Cart() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }



    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getC_num() {
        return c_num;
    }

    public void setC_num(int c_num) {
        this.c_num = c_num;
    }

    public BigDecimal getC_count() {

        BigDecimal pprice = product.getP_price();
        BigDecimal bigDecimal = new BigDecimal(c_num);

        return pprice.multiply(bigDecimal);
    }

    public void setC_count(BigDecimal c_count) {
        this.c_count = c_count;
    }
}
