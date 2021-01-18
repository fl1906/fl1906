package com.fl.service;

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
public interface AddressService {
    List<Address> findAddressByUid(int uid) throws SQLException;

    void saveAddress(Address address) throws SQLException;

    void deleteAddressByAid(String aid) throws SQLException;

    void setAddressToDefault(String aid, int uid) throws SQLException;

    void updateByAid(Address address) throws SQLException;
}
