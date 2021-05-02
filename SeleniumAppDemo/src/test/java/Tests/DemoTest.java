package Tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class DemoTest {

    public  WebDriver driver;

    @Before //once altindakini calistir demek
    public void setupDriver(){ //driver baslatiliyor 
        System.setProperty("webdriver.gecko.driver","C:\\gecko\\geckodriver-v0.29.1-win64\\geckodriver.exe"); //property'i set ettik
        driver = new FirefoxDriver();
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        System.out.println("Test initiated");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TestHome(){

        WebElement signbtn= driver.findElement(By.xpath("//*[@id=\"gg-login-enter\"]"));
        signbtn.click(); //tiklamamizi sagliyor
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement mailbox= driver.findElement(By.id("L-UserNameField"));
        mailbox.click();
        mailbox.sendKeys("Username or Email Address");

        WebElement password = driver.findElement(By.id("L-PasswordField"));
        password.click();
        password.sendKeys("Your Password");
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.id("gg-login-enter")).click();
    }
    @After //butun metodlardan sonra calisir
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit(); //driver'i kapatir
        System.out.println("Test finished.");
    }
}
