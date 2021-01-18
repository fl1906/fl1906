package com.fl.dao;

import com.fl.entity.User;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/13/15:30
 * @Description:用户模块数据库访问的接口
 * 根据用户名查询 返回对应的用户数据
 */
public interface UserDao {
    User selectUserByUname(String username) throws SQLException;
    int insertUser(User user) throws SQLException;
    User selectUserByCode(String code) throws SQLException;
    int updateStatusByUid(int uid) throws SQLException;
}
