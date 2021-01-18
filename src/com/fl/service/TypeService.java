package com.fl.service;

import com.fl.entity.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/14:51
 * @Description:
 */
public interface TypeService {
    List<Type>  findAll() throws SQLException;
}
