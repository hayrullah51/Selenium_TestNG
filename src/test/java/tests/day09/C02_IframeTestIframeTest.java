package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class C02_IframeTest {

    //Her alert JS Allert degildir sag click yapip incele diyebiliyorsak bu HTML Allert dur
    //HTML Allert'ler siradan webelement'ler locate edilip kullanilabilir
    //sag click yapamiyorsak bu bir JS Allert'tir ve switch to kullanilarak handle edilebiliriz

    /*
    ● https://the-internet.herokuapp.com/iframe adresine gidin.
    ● Bir metod olusturun: iframeTest
    ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
    ○ Text Box’a “Merhaba Dunya!” yazin.
    Merhaba Dunya!
     */

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/iframe");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test
    public void iframeTest01(){
        //○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
       WebElement baslikText = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(baslikText.isEnabled(),"Baslik yazisi erisilebilir degil!");
        System.out.println("Baslik yazisi : "+baslikText);

        // ○ Text Box’a “Merhaba Dunya!” yazin.
        driver.switchTo().frame("mce_0_ifr");
        //driver.switchTo().frame(0); //--> 0. index'den baslar
       WebElement yaziKutusu = driver.findElement(By.xpath("//*[@id='tinymce']"));
       yaziKutusu.clear();
       yaziKutusu.sendKeys("Yeni Dunya!");

       //Bir iframe'e gecis yaptiktan sonra yeniden ana sayfa ile ilgili islem yapmak istiyorsaniz
        //gecis yaptigimiz iframe'den geri donmeliyiz
        driver.switchTo().defaultContent();

        //○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu dogrulayin ve konsolda yazdirin.
        WebElement seleniumLink = driver.findElement(By.linkText("Elemental Selenium"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(seleniumLink.isDisplayed(),"Link gorulmuyor!");
        System.out.println(seleniumLink.getText());

        softAssert.assertAll();
    }
}
