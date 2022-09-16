package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class C03 {
    /*
    Yeni bir Class Olusturun : D11_SoftAssert1
    1. “https://www.hepsiburada.com/” Adresine gidin
    2. Basliginin “Turkiye’nin En Buyuk Alisveris Sitesi" icerdigini dogrulayin
    3. search kutusuna araba yazip arattirin
    4. bulunan sonuc sayisini yazdirin
    5. sonuc yazisinin "araba" icerdigini dogrulayin
    6. Sonuc yazisinin “oto” kelimesi icermedigini dogrulayin
     */

    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void test01(){
        driver.get("https://www.hepsiburada.com/");
        //2. Basliginin “Turkiye’nin En Buyuk Alisveris Sitesi" icerdigini dogrulayin
        SoftAssert softAssert = new SoftAssert();
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Hepsiburada.com"),"Title hatali !!!");

        // 3. search kutusuna araba yazip arattirin
        driver.findElement(By.className("desktopOldAutosuggestTheme-UyU36RyhCTcuRs_sXL9b")).sendKeys("araba", Keys.ENTER);

        // 4. bulunan sonuc sayisini yazdirin
       String result = driver.findElement(By.xpath("//b[@class='searchResultSummaryBar-AVnHBWRNB0_veFy34hco']")).getText();
        System.out.println(result+" tane sonuc bulundu");

        //5. sonuc yazisinin "araba" icerdigini dogrulayin
      String resultFull = driver.findElement(By.xpath("//div[@class='searchResultSummaryBar-CbyZhv5896ASVcYBLKmx']")).getText();
        System.out.println(resultFull+" fulll result");
      softAssert.assertTrue(resultFull.contains("araba"),"Sonuc yazisi araba icermiyor!!!");

      //6. Sonuc yazisinin “oto” kelimesi icermedigini dogrulayin
        softAssert.assertFalse(resultFull.contains("oto"),"sonuc yazisi oto iceriyor!!!");




        softAssert.assertAll();



    }

    @AfterClass
   public void tearDown(){
        driver.close();
    }
}
