package com.fl.dao.impl;

import com.fl.dao.TypeDao;
import com.fl.entity.Type;
import com.fl.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2021/01/09/14:55
 * @Description:
 */
public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> selectAll() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select t_id as tid,t_name as tname ,t_info as tinfo from type limit 5";
        List<Type> list=queryRunner.query(sql,new BeanListHandler<Type>(Type.class));

        return list;
    }
}
