package com.fl.service.impl;

import com.fl.dao.TypeDao;
import com.fl.dao.impl.TypeDaoImpl;
import com.fl.entity.Type;
import com.fl.service.TypeService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/14:52
 * @Description:
 */
public class TypeServiceImpl implements TypeService {

    @Override
    public List<Type> findAll() throws SQLException {
        TypeDao typeDao=new TypeDaoImpl();
        List<Type> list=typeDao.selectAll();
        return list;
    }
}
