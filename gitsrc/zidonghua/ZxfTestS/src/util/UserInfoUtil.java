import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UserInfoUtil {
    Properties userinfo;
    public UserInfoUtil(String filepath) {
        userinfo=redUserPorperties(filepath);
    }
    private Properties redUserPorperties(String filepath){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
            BufferedInputStream in = new BufferedInputStream (fileInputStream);
            try {
                properties.load (in);
            } catch (IOException e) {
                e.printStackTrace ();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        return properties;
    }
    public String getUser(String key){
        String value;
        if (userinfo.containsKey(key)){
           value = userinfo.getProperty(key);
            return value ;
        }else {
            return "";
        }
    }
    public int userInfoLines(){
        return userinfo.size();
    }

    public static void main(String[] args) {
        UserInfoUtil userInfoUtil = new UserInfoUtil("D:\\zidonghua\\ZxfTestS\\src\\util\\userinfo.properties");
        System.out.println(userInfoUtil.userInfoLines());
        int lines = userInfoUtil.userInfoLines();
        String user = null;
        for (int i = 0; i <lines ; i++) {
            user = userInfoUtil.getUser("userInfo"+i);
            String localusername = user.split(">")[0];
            String localpassword = user.split(">")[1];
            System.out.println(localusername);
            System.out.println(localpassword);
        }

    }
}
