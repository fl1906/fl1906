package com.fl.service;

import com.fl.entity.User;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/13/15:28
 * @Description:
 */
public interface UserService {
    //检测用户是否已经存在,true 表示存在
    boolean checkedUser(String username) throws SQLException;

    //注册的业务逻辑
    //user 插入数据影响的行数
    int registerUser(User user) throws SQLException;

    //激活的业务逻辑
    int activeUser(String code) throws SQLException;

    //登录的业务逻辑
    User login(String username,String password) throws SQLException;
}
