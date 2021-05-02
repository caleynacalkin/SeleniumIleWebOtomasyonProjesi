package Tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class DemoTest2 {

	public WebDriver driver;
    
    @Before //once altindakini calistir demek 
    public void setupDriver(){ //driver baslatiliyor 
        System.setProperty("webdriver.gecko.driver","C:\\gecko\\geckodriver-v0.29.1-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        System.out.println("Test initiated");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);}
    
    @Test //test classi demek 
    public void TestSearch(){

        //Arama kisminda istedigimiz urunu aratiyoruz
        WebElement searchBox = driver.findElement(By.id("search_word"));
        searchBox.click();
        searchBox.sendKeys("Bilgisayar");
        driver.findElement(By.className("header-search-find-link")).click();

        /* Arama kisminda 2.sayfa aciliyor ve rastgele bir urun aciliyor*/
        driver.findElement(By.xpath(".//*[@class='pager pt30 hidden-m gg-d-24']/a[2]")).click(); 
        driver.findElement(By.xpath(".//*[@id=\"best-match-right\"]/div[4]/ul/li[3]/a")).click();

        WebElement price= driver.findElement(By.xpath("//*[@id=\"review-brandSerial\"]"));
        String priceText= price.getText();

        /* Urunu sepete ekliyoruz*/
        WebElement quantityBox = driver.findElement(By.className("productQuantity"));
        quantityBox.click();
        quantityBox.clear();
        quantityBox.sendKeys("1");

        WebElement basketBtn = driver.findElement(By.className("sp-addbasket-button"));
        basketBtn.click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("basket-container robot-header-iconContainer-cart")).click();

        // Urunun fiyati ile sepetteki fiyati karsilastiriyoruz
        WebElement priceBasket= driver.findElement(By.className("price"));
        String priceText2= priceBasket.getText();
        if(priceText.compareTo(priceText2)>0){

            //Sepetimizdeki urun adetini arttiriyoruz
            WebElement quantityBasket = driver.findElement(By.id("purchase-incrementer"));
            quantityBasket.click();
            quantityBasket.clear();
            quantityBasket.sendKeys("1");
            driver.findElement(By.xpath(".//*[@id=\"cart-item-472117999\"]/div[1]/div[4]/div/div[1]/input")).click();
               
        }
        //Sepetimizdeki urunleri bosaltiyoruz
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id=\"cart-item-472117999\"]/div[1]/div[3]/div/div[2]/div/a[1]/i")).click();
         }
    
    @After //butun metodlardan sonra calisir
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit(); //driveri'i kapatir
        System.out.println("Test finished");
    }
}
