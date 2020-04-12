import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PorUtil {
    Properties pro;
    public PorUtil(String filepath) {
        pro = redProperties (filepath);
    }

    private Properties redProperties(String filepath){
        Properties properties= new Properties();
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
    public String getPro(String key){
        String value;
        if (pro.containsKey (key)){
            value = pro.getProperty (key);
            return value ;
        }else {
            return "";
        }
    }
    public static void main(String[] args) {
        PorUtil porUtil = new PorUtil ("D:\\zidonghua\\ZxfTestS\\target\\element.properties");
        porUtil.getPro ("username");
    }

}
