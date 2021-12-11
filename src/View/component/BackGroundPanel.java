package View.component;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    //声明图片
    private Image backIcon;

    public BackGroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//其他的组件还是交给Panel自己绘制，我们只绘制背景图片


        //绘制背景
        g.drawImage(backIcon, 0, 0, this.getWidth(), this.getHeight(), null);


    }
}
