import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Properties;

public class LoginTest {
    public WebDriver driver;
    String loginitValue ="js-signin-btn";
    String loginitGetby = "id";
    String userName = "18720973879";
    String passWord = "1540169994zxf";
    String userValue ="email";
    String userGetby = "name";
    String passValue ="password";
    String passGetby = "name";
    String loginValue ="moco-btn-lg";
    String loginGetby = "className";
    String pngValue ="header-avator";
    String pngGetby = "id";
    String userNameInfoValue ="text-ellipsis";
    String userNameInfoGetby = "className";

    public void driverInit(){
        System.setProperty("webdriver.chrome.driver", "D:\\zidonghua\\webdriver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.imooc.com/");
    }
    public void userLogin(){
        getElement(loginitGetby,loginitValue).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement emailElement = getElement(userGetby,userValue);
        WebElement passwordElement = getElement(passGetby,passValue);
        WebElement buttonElement = getElement(loginGetby,loginValue);
        emailElement.sendKeys(userName);
        passwordElement.sendKeys(passWord);
        buttonElement.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }try {
            WebElement pngElement = getElement(pngGetby,pngValue);
            Actions action = new Actions(driver);
            action.moveToElement(pngElement).perform();
            String userNameInfo = getElement(userNameInfoGetby,userNameInfoValue).getText();
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
    public By localBy(String getBy,String getValue){
        if(getBy.equals("id")){
            return By.id(getValue);
        }else if (getBy.equals("name")){
            return By.name(getValue);
        }else if (getBy.equals("className")){
            return By.className(getValue);
        }else {
            return By.xpath(getValue);
        }
    }
    public WebElement getElement(String getBy,String getValue){
        WebElement element = driver.findElement(this.localBy(getBy, getValue));
        return element;
    }

    public void getByLocal(){
        PorUtil porUtil = new PorUtil ("D:\\zidonghua\\ZxfTestS\\target\\element.properties");
    }



    public static void main(String[] args) {
        LoginTest loginTest = new LoginTest();
        loginTest.driverInit();
        loginTest.userLogin();
    }

}
