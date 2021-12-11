package View.ui;

import Dao.UserDao;
import Util.JDBCUtils;
import Util.StringUtils;
import View.component.BackGroundPanel;
import View.util.PathUtils;
import View.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import Module.User;

/*
static Component createHorizontalStrut(int width)
          创建一个不可见的、固定宽度的组件。

static Component createVerticalStrut(int height)
          创建一个不可见的、固定高度的组件
          
String trim() 
          返回字符串的副本，忽略前导空白和尾部空白

V put(K key, V value)
          将指定的值与此映射中的指定键关联（可选操作）。
 */
public class MainInterface {

    JFrame jFrame = new JFrame("小博图书馆");

    final int WIDTH = 500;
    final int HEIGHT = 300;


    //组装视图
    public void init() throws IOException {
        //设置窗口相关属性
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        jFrame.setResizable(false);//窗口不可缩放

        jFrame.setIconImage(ImageIO.read(new File(PathUtils.getPath("菱形.png"))));



        //设置窗口内容
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getPath("登录.png"))));

        //组装登录相关元素
        Box box = Box.createVerticalBox();

        //组装用户名
        Box uBox = Box.createHorizontalBox();

        JLabel uLabel = new JLabel("用户名:");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));//添加间隔
        uBox.add(uField);


        //组装密码
        Box pBox = Box.createHorizontalBox();

        JLabel pLabel = new JLabel("密   码:");
        JTextField pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        JButton registerBtn = new JButton("注册");

        //为按钮添加监听
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户数据
                String username = uField.getText().trim();//
                String password = pField.getText().trim();//

                //先判断用户名和密码是否为空
                if(StringUtils.isEmpty(username, password)){
                    JOptionPane.showMessageDialog(jFrame, "用户名和密码不能为空");
                    return;
                }
                if(StringUtils.isEmpty(username)){
                    JOptionPane.showMessageDialog(jFrame, "用户名不能为空");
                    return;
                }
                if(StringUtils.isEmpty(password)){
                    JOptionPane.showMessageDialog(jFrame, "密码不能为空");
                    return;
                }

                //访问登录接口
                try {
                    //先获取连接
                    Connection connection = JDBCUtils.getConnection();

                    //得到User类的对象，调用它的方法
                    UserDao userDao = new UserDao();

                    //然后调用登录的方法
                    User user = userDao.login(connection, username, password);
                    if(user != null){
                        //跳转到主界面
                        new ManageInterface().init();
                    }else{
                        JOptionPane.showMessageDialog(jFrame, "用户名或密码错误");
                        return;
                    }

                    jFrame.dispose();

                    //关闭连接
                    JDBCUtils.close(connection);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转到注册页面
                try {
                    new RegisterInterface().init();

                    //登录页面消失
                    jFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        bBox.add(loginBtn);
        bBox.add(Box.createHorizontalStrut(80));
        bBox.add(registerBtn);


        //组装全部组件
        box.add(Box.createVerticalStrut(80));//组件和窗口上方的间隔
        box.add(uBox);
        box.add(Box.createVerticalStrut(40));//用户名和密码之间的间隔
        box.add(pBox);
        box.add(Box.createVerticalStrut(60));//密码和按钮之间的间隔
        box.add(bBox);


        bgPanel.add(box);

        jFrame.add(bgPanel);

        jFrame.setVisible(true);//设置窗口可见
    }


    //客户端程序的入口
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
