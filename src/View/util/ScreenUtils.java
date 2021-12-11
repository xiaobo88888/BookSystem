package View.util;

import java.awt.*;

public class ScreenUtils {

    //获取当前电脑屏幕宽度
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }


    //获取当前电脑屏幕高度
    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
