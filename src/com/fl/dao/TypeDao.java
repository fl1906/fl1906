package com.fl.dao;

import com.fl.entity.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/14:53
 * @Description:
 */
public interface TypeDao {

    List<Type> selectAll() throws SQLException;

}
