package com.fl.dao;

import com.fl.entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/16/15:48
 * @Description:
 */
public interface AddressDao {
    List<Address> selectAddressByUid(int uid) throws SQLException;

    void insertAddress(Address address) throws SQLException;

    void deleteAddress(String aid) throws SQLException;

    void updateAddressToDefault(String aid) throws SQLException;

    void updateAddressToCommons(String aid, int uid) throws SQLException;

    void updateAddressByAid(Address address) throws SQLException;
}
