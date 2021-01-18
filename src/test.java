import com.fl.entity.User;
import com.fl.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/13/16:16
 * @Description:
 */
public class test {
    public static void main(String[] args) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        //查询sql
        String sql="select u_id as uid,u_name as username, " +
                "u_password as upassword, u_sex as usex, u_status as ustatus," +
                "u_code as code,u_email as email,u_role as urole from user where u_name=?";
        User user= queryRunner.query(sql,new BeanHandler<User>(User.class),"fl1906");
        System.out.println(user.getUsername()+ " "+user.getUpassword());
    }
}
