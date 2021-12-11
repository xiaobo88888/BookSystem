package Util;

/**
 * 封装了判断字符串是否为空的方法
 */
public class StringUtils {
    public static boolean isEmpty(String string){
        if (string==null||"".equals(string.trim())) {
            return true;
        }else {
            return false;
        }
    }
    public static boolean isEmpty(String username, String password){
        if((username==null||"".equals(username.trim()) && (password==null||"".equals(password.trim())))){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isEmpty(String username, String password, String phonenumber, String sex){
        if((username==null||"".equals(username.trim())) && (password==null||"".equals(password.trim())) && (phonenumber==null||"".equals(phonenumber.trim())) && (sex==null||"".equals(sex.trim()))){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isEmpty(String title, String stock, String author, String price, String introduce){
        if((title==null||"".equals(title.trim())) && (stock==null||"".equals(stock.trim())) && (author==null||"".equals(author.trim())) && (price==null||"".equals(price.trim())) && (introduce==null||"".equals(introduce.trim()))){
            return true;
        }else{
            return false;
        }
    }


    public static boolean isNotEmpty(String string) {
        if (string!=null&&"".equals(string.trim())) {
            return true;
        } else {
            return false;
        }
    }

}
