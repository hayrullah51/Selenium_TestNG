package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class Test_Youtube {

    /*
        1) Bir class oluşturun: YoutubeAssertions
        2) https://www.youtube.com adresine gidin
        3) Aşağıdaki adları kullanarak
        4) test metodu oluşturun ve gerekli testleri yapin
        ○ titleTest=> Sayfa başlığının “YouTube” oldugunu test edin
        ○ imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin
        ○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
        ○ wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin
     */

    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.youtube.com/");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

    @org.testng.annotations.Test
    public void titleTest(){
        Assert.assertEquals(driver.getTitle(),"YouTube","Sayfa basligi 'YouTube' degil!");
    }

    @org.testng.annotations.Test
    public void imageTest(){
        Assert.assertTrue(driver.findElement(By.xpath("(//yt-icon[@class='style-scope ytd-logo'])[1]")).isDisplayed(),"Image gorulmuyor!");
    }

    @org.testng.annotations.Test
    public void wrongTitleTest(){
        Assert.assertNotEquals(driver.getTitle(),"youtube","Title malesef 'youtube'");
    }
}
