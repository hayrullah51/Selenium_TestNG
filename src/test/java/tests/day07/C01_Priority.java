package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class C01_Priority {

    //3 test method'u olusturalim
    // 1. amazon ana sayfasina gitsin
    // 1. facebook ana sayfasina gitsin
    // 1. hepsiburada ana sayfasina gitsin
    //sayfa title'lerini yazdirin

    /*
    Priority yazmadigimiz method'lari = 0 kabul eder
    priority olmayanlari kendi icinde siralayip olmayanlari priority degerlerine uygun olarak calistirir
     */

    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test (priority = -1)
    public void amazonTest(){
        driver.get("https://www.amazon.com/");
        System.out.println(driver.getTitle());
    }

    @Test
    public void facebookTest(){
        driver.get("https://www.facebook.com/");
        System.out.println(driver.getTitle());
    }

    @Test (priority = 0)
    public void hepsiburadaTest(){
        driver.get("https://www.hepsiburada.com/");
        System.out.println(driver.getTitle());
    }
}
