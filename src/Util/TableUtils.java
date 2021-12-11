package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class TableUtils {
    public static void tableSelect(Vector<Vector> data){
        //展示数据
        try{
            //获取连接
            Connection connection = JDBCUtils.getConnection();

            //编写sql语句
            String sql = "select * from book";

            //得到PreparedStatement对象
            PreparedStatement ps = connection.prepareStatement(sql);

            //执行，并返回结果集
            ResultSet rs = ps.executeQuery();

            //清空一下表格
            data.clear();

            //处理结果集
            while(rs.next()){
                Vector hang = new Vector();
                hang.add(rs.getInt(1));//id
                hang.add(rs.getString(2));//书名
                hang.add(rs.getString(3));//作者
                hang.add(rs.getString(4));//简介
                hang.add(rs.getDouble(5));//价格
                hang.add(rs.getInt(6));//库存

                //然后总的加入
                data.add(hang);
            }

            //关闭资源
            JDBCUtils.close(connection, ps, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
