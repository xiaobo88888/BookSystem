package View.ui;

import Util.JDBCUtils;
import View.component.BookManageComponent;
import View.util.PathUtils;
import View.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

public class ManageInterface {

    JFrame jFrame = new JFrame("小博图书馆欢迎你！");

    final int WIDTH = 1000;
    final int HEIGHT = 600;

    public void init() throws IOException {
        //给窗口设置属性
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setIconImage(ImageIO.read(new File(PathUtils.getPath("主界面.jpg"))));


        //设置菜单栏
        JMenuBar jmb = new JMenuBar();
        JMenu jMenu = new JMenu("设置");
        JMenuItem m1 = new JMenuItem("切换账号");
        JMenuItem m2 = new JMenuItem("退出程序");

        //处理菜单项点击事件
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();

                    jFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenu.add(m1);
        jMenu.add(m2);
        jmb.add(jMenu);

        jFrame.setJMenuBar(jmb);

        //设置分割面板
        JSplitPane jsp = new JSplitPane();

        jsp.setContinuousLayout(true);//支持连续布局
        jsp.setDividerLocation(150);//设置位置
        jsp.setDividerSize(7);//设置大小(宽度)

        jFrame.add(jsp);


        //设置左边的树

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode useManage = new DefaultMutableTreeNode("用户管理");
        DefaultMutableTreeNode bookManage = new DefaultMutableTreeNode("图书管理");
        DefaultMutableTreeNode borrowManage = new DefaultMutableTreeNode("借阅管理");
        DefaultMutableTreeNode statisticsManage = new DefaultMutableTreeNode("统计管理");

        //建立层级关系
        root.add(useManage);
        root.add(bookManage);
        root.add(borrowManage);
        root.add(statisticsManage);

        JTree jTree = new JTree(root);

        //修改背景色
        Color color = new Color(203, 220, 217);
        jTree.setBackground(color);

        //创建结点绘制器对象
        MyRenderer myRenderer = new MyRenderer();
        jTree.setCellRenderer(myRenderer);

        myRenderer.setBackgroundNonSelectionColor(color);//设置未选中结点颜色
        myRenderer.setBackgroundSelectionColor(new Color(140,140,140));//设置选中结点颜色

        jTree.setSelectionRow(2);//默认选中图书管理——第三行的

        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            //当选中的条目变换后，会调用此方法
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //得到当前选中的条目
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();

                if(useManage.equals(lastPathComponent)){
                    jsp.setRightComponent(new JLabel("这里进行用户管理..."));
                    jsp.setDividerLocation(150);
                }else if(bookManage.equals(lastPathComponent)){
                    jsp.setRightComponent(new BookManageComponent(jFrame));
                    jsp.setDividerLocation(150);
                }else if(borrowManage.equals(lastPathComponent)){
                    jsp.setRightComponent(new JLabel("这里进行借阅管理..."));
                    jsp.setDividerLocation(150);

                }else if(statisticsManage.equals(lastPathComponent)){
                    jsp.setRightComponent(new JLabel("这里进行统计管理..."));
                    jsp.setDividerLocation(150);

                }
            }
        });


        jsp.setRightComponent(new BookManageComponent(jFrame));
        jsp.setLeftComponent(jTree);


        jFrame.setVisible(true);


        //查询用户信息，左上角显示"欢迎XXX"
    }

    /*public static void main(String[] args) {
        try {
            new ManageInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    //自定义结点绘制器
    private class MyRenderer extends DefaultTreeCellRenderer{
        private ImageIcon rootIcon = null;
        private ImageIcon useManageIcon = null;
        private ImageIcon bookManageIcon = null;
        private ImageIcon borrowManageIcon = null;
        private ImageIcon statisticsManageIcon = null;

        public MyRenderer(){
            rootIcon = new ImageIcon(PathUtils.getPath("三角形.png"));
            useManageIcon = new ImageIcon(PathUtils.getPath("五角星.png"));
            bookManageIcon = new ImageIcon(PathUtils.getPath("五边形.png"));
            borrowManageIcon = new ImageIcon(PathUtils.getPath("六角形.png"));
            statisticsManageIcon = new ImageIcon(PathUtils.getPath("菱形.png"));

        }
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            //使用默认绘制
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            ImageIcon image = null;

            switch (row){
                case 0:
                    image = rootIcon;
                    break;
                case 1:
                    image = useManageIcon;
                    break;
                case 2:
                    image = bookManageIcon;
                    break;
                case 3:
                    image = borrowManageIcon;
                    break;
                case 4:
                    image = statisticsManageIcon;
                    break;
            }

            this.setIcon(image);
            return this;
        }
    }
}
