package Dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import Module.Book;

/**
 * 封装对book表的操作
 */
public class BookDao {

    /**
     * 插入图书
     * @param connection
     * @param title
     * @param author
     * @param introduce
     * @param price
     * @param stock
     */
    public void bookInsert(Connection connection, String title, String author, String introduce, double price, int stock){
        try {
            //编写sql语句
            String sql = "insert into book (title, author, introduce, price, stock) values(?, ?, ?, ?, ?)";

            //创建QueryRunner对象
            QueryRunner runner = new QueryRunner();

            //调用方法
            runner.update(connection, sql, title, author, introduce, price, stock);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 更新图书
     * @param connection
     * @param title
     * @param author
     * @param introduce
     * @param price
     * @param stock
     * @param id
     */
    public void bookUpdate(Connection connection, String title, String author, String introduce, double price, int stock, int id){
        try{
            //编写sql语句
            String sql = "update book set title = ?, author = ?, introduce = ?, price = ?, stock = ? where id = ?";

            //创建QueryRunner对象
            QueryRunner runner = new QueryRunner();

            //调用方法
            runner.update(connection, sql, title, author, introduce, price, stock, id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过id查询一条纪录
     * @param connection
     * @param id
     * @return
     */
    public Book bookSelect(Connection connection, int id){
        Book book = null;
        try{
            //编写sql语句
            String sql = "select title, author, introduce, price, stock from book where id = ?";

            //创建QueryRunner对象
            QueryRunner runner = new QueryRunner();

            //创建ResultSetHandle的实现类
            BeanHandler<Book> handle = new BeanHandler<>(Book.class);

            //调用查询方法
            book = runner.query(connection, sql, handle, id);

            System.out.println(book);

        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    public void bookDelete(Connection connection, int id){
        try{
            //编写sql语句
            String sql = "delete from book where id = ?";

            //创建QueryRunner对象
            QueryRunner runner = new QueryRunner();

            //调用方法
            runner.update(connection, sql, id);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
