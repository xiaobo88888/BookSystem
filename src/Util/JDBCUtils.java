package Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * 实现数据库的连接和关闭
 */
public class JDBCUtils {
    /**
     * 创建连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //创建文件的流对象
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("Druid.properties");

        //创建Properties
        Properties properties = new Properties();

        //读取流对象
        properties.load(is);

        //创建数据库连接池
        DataSource source = DruidDataSourceFactory.createDataSource(properties);

        //创建连接
        Connection connection = source.getConnection();

        //返回连接
        return connection;
    }

    /**
     * 关闭连接
     * @param connection
     */
    public static void close(Connection connection){
        //快速关闭连接
        DbUtils.closeQuietly(connection);
    }

    public static void close(Connection connection, PreparedStatement ps, ResultSet rs){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }

}
