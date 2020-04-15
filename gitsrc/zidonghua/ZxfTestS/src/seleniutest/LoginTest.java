import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class LoginTest {
    public WebDriver driver;
    public void driverInit(){
        System.setProperty("webdriver.chrome.driver", "D:\\zidonghua\\webdriver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.imooc.com/");
    }
    @Test
    public void userLogin(){
        UserInfoUtil userInfoUtil = new UserInfoUtil("D:\\zidonghua\\ZxfTestS\\src\\util\\userinfo.properties");
        int lines = userInfoUtil.userInfoLines();
        String user ;
        for (int i = 0; i <lines ; i++) {
            driverInit();
            user = userInfoUtil.getUser("userInfo"+i);
            String localusername = user.split(">")[0];
            String localpassword = user.split(">")[1];
            getElement("loginit").click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement emailElement = getElement("username");
            WebElement passwordElement = getElement("passname");
            WebElement buttonElement = getElement("loginname");
            emailElement.sendKeys(localusername);
            passwordElement.sendKeys(localpassword );
            buttonElement.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }try {
                WebElement pngElement = getElement("pngname");
                Actions action = new Actions(driver);
                action.moveToElement(pngElement).perform();
                String userNameInfo = getElement("userNameInfoname").getText();
                if(userNameInfo.equals("晓风残月0101")){
                    System.out.println("登陆成功");
                }else{
                    System.out.println("用户信息获取失败");
                }
            }catch (Exception e){
                System.out.println("登陆失败");
            }
            driver.close();
        }

    }
    public By localBy(String key){
        PorUtil porUtil = new PorUtil("D:\\zidonghua\\ZxfTestS\\src\\util\\element.properties");
        String value = porUtil.getPro(key);
        String localGetby;
        String localValue;
        localGetby = value.split(">")[0];
        localValue = value.split(">")[1];
        if(localGetby.equals("id")){
            return By.id(localValue);
        }else if (localGetby.equals("name")){
            return By.name(localValue);
        }else if (localGetby.equals("className")){
            return By.className(localValue);
        }else {
            return By.xpath(localValue);
        }
    }
    public WebElement getElement(String key){
        WebElement element = driver.findElement(this.localBy(key));
        return element;
    }
}
