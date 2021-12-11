package View.component;

import Dao.BookDao;
import Util.JDBCUtils;
import Util.StringUtils;
import View.util.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;
import java.sql.Connection;
import Module.Book;

public class UpdateBookDialog extends JDialog {

    final int WIDTH = 400;
    final int HEIGHT = 300;
    private String id;


    //声明在这里是为了访问后台数据方便写入
    private JTextField nField;
    private JTextField cField;
    private JTextField aField;
    private JTextField pField;
    private JTextArea jArea;

//    private ActionDoneListener listener;

    public UpdateBookDialog(JFrame jFrame, String title, boolean isModel, String id){  //这里多了一个参数id，目的是确认点击的是第几行
        super(jFrame, title, isModel);

        this.id = id;
//        this.listener = listener;

        //设置窗口大小和位置
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);


        Box box = Box.createVerticalBox();


        //组装图书名称
        Box nBox = Box.createHorizontalBox();

        JLabel nLabel = new JLabel("图书名称:");
        nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(20));
        nBox.add(nField);

        //组装图书库存
        Box cBox = Box.createHorizontalBox();

        JLabel cLabel = new JLabel("图书库存:");
        cField = new JTextField(15);

        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(20));
        cBox.add(cField);

        //组装图书作者
        Box aBox = Box.createHorizontalBox();

        JLabel aLabel = new JLabel("图书作者:");
        aField = new JTextField(15);

        aBox.add(aLabel);
        aBox.add(Box.createHorizontalStrut(20));
        aBox.add(aField);

        //组装图书价格
        Box pBox = Box.createHorizontalBox();

        JLabel pLabel = new JLabel("图书价格:");
        pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装图书简介
        Box jBox = Box.createHorizontalBox();

        JLabel jLabel = new JLabel("图书简介:");
        jArea = new JTextArea(3, 15);

        jBox.add(jLabel);
        jBox.add(Box.createHorizontalStrut(20));
        jBox.add(new JScrollPane(jArea));

        //组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");

        Book book = null;

        //先读取选中的纪录
        try {
            //获取连接
            Connection connection = JDBCUtils.getConnection();

            //得到BookDao类的对象，调用它的方法
            BookDao bookDao = new BookDao();

            //调用查询方法，通过id查询
            book = bookDao.bookSelect(connection, Integer.valueOf(id).intValue());

            //关闭资源
            JDBCUtils.close(connection);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        //将读取到的内容设置到各个文本上
        nField.setText(book.getTitle());//设置名称
        cField.setText(String.valueOf(book.getStock()));//设置库存
        aField.setText(book.getAuthor());//设置作者
        pField.setText(String.valueOf(book.getPrice()));//设置价格
        jArea.setText(book.getIntroduce());//设置简介

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //然后再次读取用户修改的值
                String title = nField.getText().trim();
                String stock = cField.getText().trim();
                String author = aField.getText().trim();
                String price = pField.getText().trim();
                String introduce = jArea.getText().trim();

                //再确保各部分不要空值
                if(StringUtils.isEmpty(title, stock, author, price, introduce)){
                    JOptionPane.showMessageDialog(jFrame, "请填写完整用户信息");
                    return;
                }
                if(StringUtils.isEmpty(title)){
                    JOptionPane.showMessageDialog(jFrame, "用户名不能为空");
                    return;
                }
                if(StringUtils.isEmpty(stock)){
                    JOptionPane.showMessageDialog(jFrame, "库存不能为空");
                    return;
                }
                if(StringUtils.isEmpty(author)){
                    JOptionPane.showMessageDialog(jFrame, "作者不能为空");
                    return;
                }
                if(StringUtils.isEmpty(price)){
                    JOptionPane.showMessageDialog(jFrame, "价格不能为空");
                    return;
                }
                //访问后台接口
                try{
                    //获取连接
                    Connection connection = JDBCUtils.getConnection();

                    //得到BookDao类的对象，调用它的方法
                    BookDao bookDao = new BookDao();

                    //调用修改方法
                    bookDao.bookUpdate(connection, title, author, introduce, Double.valueOf(price), Integer.valueOf(stock),Integer.valueOf(id));

                    //关闭资源
                    JDBCUtils.close(connection);
                }catch (Exception ex){
                    ex.printStackTrace();
                }


                JOptionPane.showMessageDialog(jFrame, "修改成功！！！");
                dispose();//当前对话框隐藏

            }
        });

        bBox.add(updateBtn);


        //组装全部
        box.add(Box.createVerticalStrut(20));
        box.add(nBox);
        box.add(Box.createVerticalStrut(15));
        box.add(cBox);
        box.add(Box.createVerticalStrut(15));
        box.add(aBox);
        box.add(Box.createVerticalStrut(15));
        box.add(pBox);
        box.add(Box.createVerticalStrut(15));
        box.add(jBox);
        box.add(Box.createVerticalStrut(15));
        box.add(bBox);


        //为了左右有间隔，再定义一个水平的Box
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(box);
        hBox.add(Box.createHorizontalStrut(20));


        this.add(hBox);

    }


}
