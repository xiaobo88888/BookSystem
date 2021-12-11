package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import Module.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * 封装对User表的操作
 */
public class UserDao {
    /**
     * 验证登录
     * @param connection
     * @return
     */
    public User login(Connection connection, String username, String password){
        User user1 = null;
        try {
            //编写sql语句
            String sql = "select username, password from user where username = ? and password = ?";

            //创建QueryRunner对象
            QueryRunner runner = new QueryRunner();

            //创建ResuleSetHandle的实现类
            BeanHandler<User> handler = new BeanHandler<User>(User.class);

            //查询，调用query方法
            user1 = runner.query(connection, sql, handler, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user1;
    }

    /**
     * 注册用户
     * @param connection
     * @param username
     * @param password
     * @param phonenumber
     * @param sex
     */
    public void register(Connection connection, String username, String password, String phonenumber, String sex){
        try{
            //编写sql语句
            String sql = "insert into user (username, password, sex, phonenumber) values(?, ?, ?, ?)";

            //创建QueryRunner对象
            QueryRunner runner = new QueryRunner();

            //调用通用的增删改方法
            runner.update(connection, sql, username, password, sex, phonenumber);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
