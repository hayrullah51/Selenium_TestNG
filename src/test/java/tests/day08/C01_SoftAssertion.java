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

public class C01_SoftAssertion {
    //amazon sayfasina git
    // url'i amazon iverdigini test edin
    //titlenin amazon icerdigini test edin
    //java aratin ve ilk urunun java kelimesi icerdigini test edin

    SoftAssert softAssert = new SoftAssert();
    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }
    @AfterClass
    public void tearDown(){
        driver.close();
        //softAssert' lar pass olsa da olmasada asserAll'a kadar tum satirlar calisir
        //ama eger testlerden bir tanesi bile failed ise assertAll'dan sonra execution stops
    }

    // url'i amazon iverdigini test edin
    @Test
    public void urlTest(){
        //Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/"));
        softAssert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/"));
        softAssert.assertAll();
    }

    @Test
    public void titleTest (){
        //Assert.assertTrue(driver.getTitle().contains("Amazon"));
        softAssert.assertTrue(driver.getTitle().contains("Amazon"));
        softAssert.assertAll();
    }

    @Test
    public void aramaTest(){
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java"+ Keys.ENTER);

    }

    @Test(dependsOnMethods = "aramaTest")
    public void ilkUrunTest(){
       WebElement ilk_urun = driver.findElement(By.xpath("//span[text()='Head First Java: A Brain-Friendly Guide']"));
        //Assert.assertTrue(ilk_urun.getText().contains("Java"));
        softAssert.assertTrue(ilk_urun.getText().contains("Java"),"ilk urunde java yok");
        softAssert.assertAll();
    }



}
