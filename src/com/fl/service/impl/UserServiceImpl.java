package com.fl.service.impl;

import com.fl.dao.UserDao;
import com.fl.dao.impl.UserDaoImpl;
import com.fl.entity.User;
import com.fl.service.UserService;
import com.fl.utils.Constants;
import com.fl.utils.EmailUtils;
import com.fl.utils.MD5Utils;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/13/15:30
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public boolean checkedUser(String username) throws SQLException {
        //创建dao访问对象
        UserDao userDao=new UserDaoImpl();
        //执行结果
        User user=userDao.selectUserByUname(username);
        if(user!=null)
        {
            return true;
        }else return false;

    }

    @Override
    public int registerUser(User user) throws SQLException {
        //用户保存到数据库
          UserDao userDao=new UserDaoImpl();
          int row=userDao.insertUser(user);
          //发送邮件
        EmailUtils.sendEmail(user);
        return row;
    }

    @Override
    public int activeUser(String code) throws SQLException {
        //1,根据激活码查找用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUserByCode(code);
        if (user == null) {
            return 0;
        }
        //2,判断用户是否激活
        if ((Constants.USER_ACTIVE).equals(user.getUstatus())) {
            return 2;
        }
        //3,进行激活操作
        int i = userDao.updateStatusByUid(user.getUid());
        if (i > 0)
        {
            return 1;
        }

        return 0;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        //密码要先解密MD5
       String md5Password= MD5Utils.md5(password);
       //根据用户名查找用户
        UserDao userDao=new UserDaoImpl();
      User user= userDao.selectUserByUname(username);
        if (user != null&&user.getUpassword().equals(md5Password)) {
            return user;
        }
        return null;
    }
}
