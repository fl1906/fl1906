package com.fl.service.impl;

import com.fl.dao.AddressDao;
import com.fl.dao.impl.AddressDaoImpl;
import com.fl.entity.Address;
import com.fl.service.AddressService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/16/15:48
 * @Description:
 */
public class AddressServiceImpl implements AddressService {
    @Override
    public List<Address> findAddressByUid(int uid) throws SQLException {
        AddressDao addressDao=new AddressDaoImpl();
       List<Address> addresses=addressDao.selectAddressByUid(uid);
        return addresses;
    }

    @Override
    public void saveAddress(Address address) throws SQLException {
        AddressDao addressDao=new AddressDaoImpl();
        addressDao.insertAddress(address);
    }

    @Override
    public void deleteAddressByAid(String aid) throws SQLException {
        AddressDao addressDao=new AddressDaoImpl();
        addressDao.deleteAddress(aid);
    }

    @Override
    public void setAddressToDefault(String aid, int uid) throws SQLException {
        AddressDao addressDao=new AddressDaoImpl();
        //将aid 的地址改为默认地址
          addressDao.updateAddressToDefault(aid);
        //将非aid的地址改为非默认地址
        addressDao.updateAddressToCommons(aid,uid);
    }

    @Override
    public void updateByAid(Address address) throws SQLException {
        AddressDao addressDao=new AddressDaoImpl();
        addressDao.updateAddressByAid(address);
    }
}
