package com.fl.dao.impl;

import com.fl.dao.UserDao;
import com.fl.entity.User;
import com.fl.utils.C3P0Utils;
import com.fl.utils.Constants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/13/15:34
 * @Description:数据访问实现类
 */
@SuppressWarnings("all")
public class UserDaoImpl implements UserDao {
        @Override
        public User selectUserByUname(String username) throws SQLException {
            QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
            //查询sql
            String sql="select u_id as uid,u_name as username, " +
                    "u_password as upassword, u_sex as usex, u_status as ustatus," +
                    "u_code as code,u_email as email,u_role as urole from user where u_name=?";
            User user= queryRunner.query(sql,new BeanHandler<User>(User.class),username);
            //System.out.println(user.getUsername()+ " "+user.getUpassword());
            return user;
        }

    @Override
    public int insertUser(User user) throws SQLException {
            QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
            String sql="insert into user(u_name,u_password,u_sex,u_status,u_code,u_email,u_role)" +
                    "value(?,?,?,?,?,?,?)";
          int rows= queryRunner.update(sql,user.getUsername(),user.getUpassword(),user.getUsex()
            ,user.getUstatus(),user.getCode(),user.getEmail(),user.getUrole());
        return rows;
    }

    @Override
    public User selectUserByCode(String code) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        //查询sql
        String sql="select u_id as uid,u_name as username, " +
                "u_password as upassword, u_sex as usex, u_status as ustatus," +
                "u_code as code,u_email as email,u_role as urole from user where u_code=?";
        User user= queryRunner.query(sql,new BeanHandler<User>(User.class),code);
        return user;
    }

    @Override
    public int updateStatusByUid(int uid) throws SQLException {
            QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
            String sql="update user set u_status=? where u_id=?";
          int row=  queryRunner.update(sql, Constants.USER_ACTIVE,uid);

        return row;
    }
}
