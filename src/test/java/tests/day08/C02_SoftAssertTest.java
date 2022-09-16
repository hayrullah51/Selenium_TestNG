package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C02_SoftAssertTest {
    /*
    Yeni bir Class Olusturun : C03_SoftAssert
    1. “http://zero.webappsecurity.com/” Adresine gidin
    2. Sign in butonuna basin
    3. Login kutusuna “username” yazin
    4. Password kutusuna “password” yazin
    5. Sign in tusuna basin
    6. Pay Bills sayfasina gidin
    7. “Purchase Foreign Currency” tusuna basin
    8. “Currency” drop down menusunden Eurozone’u secin
    9. soft assert kullanarak "Eurozone (Euro)" secildigini test edin
    10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin
    "Select One", "Australia (dollar)", "Canada (dollar)",
    "Switzerland (franc)","China (yuan)","Denmark (krone)","Eurozone (euro)",
    "Great Britain (pound)","Hong Kong (dollar)","Japan (yen)","Mexico (peso)","Norway (krone)","New Zealand
(dollar)","Sweden (krona)","Singapore (dollar)","Thailand (baht)"
     */

    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("http://zero.webappsecurity.com/");
    }
    @AfterClass
    public void tearDown(){
       // driver.close();
    }

    @Test
    public void test01(){
        driver.findElement(By.id("signin_button")).click();
        driver.findElement(By.id("user_login")).sendKeys("username");
        driver.findElement(By.id("user_password")).sendKeys("password");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        driver.navigate().back();
        driver.findElement(By.id("onlineBankingMenu")).click();
        driver.findElement(By.id("pay_bills_link")).click();
        driver.findElement(By.linkText("Purchase Foreign Currency")).click();


        WebElement dropDown = driver.findElement(By.id("pc_currency"));
        Select select = new Select(dropDown);
        select.selectByValue("EUR");

        //9. soft assert kullanarak "Eurozone (Euro)" secildigini test edin
        String actualData = select.getFirstSelectedOption().getText();
        String exceptedValue= "Eurozone (euro)";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualData,exceptedValue,"secilen option Euro degil");
        //10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin
        List<WebElement> tumOpsiyonlar = select.getOptions();
        //option listesi web elementlerden olusur
        //excepted ise String, bunun icin opsiyon listesini String yapmaliyiz


        tumOpsiyonlar.stream().forEach(t-> System.out.print("'"+t.getText()+"' ,"));

        List<String> tumOpsiyonlarString = new ArrayList<>();
        for (WebElement each:tumOpsiyonlar
             ) {
            tumOpsiyonlarString.add(each.getText());
        }
        List<String> exceptedOptionsList = Arrays.asList("\"Select One\", \"Australia (dollar)\", \"Canada (dollar)\",\n" +
                "    \"Switzerland (franc)\",\"China (yuan)\",\"Denmark (krone)\",\"Eurozone (euro)\",\n" +
                "    \"Great Britain (pound)\",\"Hong Kong (dollar)\",\"Japan (yen)\",\"Mexico (peso)\",\"Norway (krone)\",\"New Zealand\n" +
                "(dollar)\",\"Sweden (krona)\",\"Singapore (dollar)\",\"Thailand (baht)\"");
        softAssert.assertEquals(tumOpsiyonlarString,exceptedOptionsList,"Liste farkli!");

        softAssert.assertAll();

    }
}
