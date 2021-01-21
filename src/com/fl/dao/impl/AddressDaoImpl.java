package com.fl.dao.impl;

import com.fl.dao.AddressDao;
import com.fl.entity.Address;
import com.fl.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/16/15:48
 * @Description:
 */
public class AddressDaoImpl implements AddressDao {
    @Override
    public List<Address> selectAddressByUid(int uid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select a_id as aid, u_id as uid,a_name as aname,a_phone " +
                "as aphone,a_detail as adetail ,a_state as astate from " +
                "address where u_id = ? order by a_state desc;";
        List<Address> list = queryRunner.query(sql, new BeanListHandler<Address>(Address.class), uid);
        return list;
    }

    @Override
    public void insertAddress(Address address) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="insert into address (u_id,a_name,a_phone,a_detail,a_state) value (?,?,?,?,?);";
        queryRunner.update(sql,address.getUid(),address.getAname(),address.getAphone(),address.getAdetail(),address.getAstate());
    }

    @Override
    public void deleteAddress(String aid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql1="SET FOREIGN_KEY_CHECKS = 0;";//关闭外键检查
        String sql2="SET FOREIGN_KEY_CHECKS = 1;";//开启外键检查
        queryRunner.update(sql1);
        String sql="delete from address where a_id=?";
        queryRunner.update(sql,aid);
        queryRunner.update(sql2);

    }

    @Override
    public void updateAddressToDefault(String aid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="update address set a_state =1 where a_id=?;";
        queryRunner.update(sql,aid);
    }

    @Override
    public void updateAddressToCommons(String aid, int uid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql= "update address set a_state=0 where a_id!=? and u_id=?;";
        queryRunner.update(sql,aid,uid);
    }

    @Override
    public void updateAddressByAid(Address address) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="update address set a_state=?,a_name=?,a_phone=?,a_detail=? where a_id=?;";
         queryRunner.update(sql,address.getAstate(),address.getAname(),address.getAphone(),address.getAdetail(),address.getAid());
    }
}
