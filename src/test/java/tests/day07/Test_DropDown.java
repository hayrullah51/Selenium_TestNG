package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test_DropDown {

    /*
    1. http://zero.webappsecurity.com/ Adresine gidin
    2. Sign in butonuna basin
    3. Login kutusuna “username” yazin
    4. Password kutusuna “password.” yazin
    5. Sign in tusuna basin
    6. Pay Bills sayfasina gidin
    7. “Purchase Foreign Currency” tusuna basin
    8. “Currency” drop down menusunden Eurozone’u secin
    9. “amount” kutusuna bir sayi girin
    10. “US Dollars” in secilmedigini test edin
    11. “Selected currency” butonunu secin
    12. “Calculate Costs” butonuna basin sonra “purchase” butonuna basin
    13. “Foreign currency cash was successfully purchased.” yazisinin ciktigini kontrol edin.
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

    //2. Sign in butonuna basin
    @Test(priority = 1)
    public void singinTest(){
        driver.findElement(By.id("signin_button")).click();
    }

    //3. Login kutusuna “username” yazin
    //4. Password kutusuna “password.” yazin
    //5. Sign in tusuna basin
    @Test(priority = 2)
    public void userEnterNamePasswordTest(){
        driver.findElement(By.id("user_login")).sendKeys("username");
        driver.findElement(By.id("user_password")).sendKeys("password");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        driver.navigate().back();
    }

    //6. Pay Bills sayfasina gidin
    @Test(priority = 3)
    public void payPageTest(){
        driver.findElement(By.id("onlineBankingMenu")).click();
        driver.findElement(By.id("pay_bills_link")).click();
    }

    //7. “Purchase Foreign Currency” tusuna basin
    @Test(priority = 4)
    public void pfcPage(){
        driver.findElement(By.linkText("Purchase Foreign Currency")).click();
    }

    //8. “Currency” drop down menusunden Eurozone’u secin
    @Test(priority = 5)
    public void dropDownTest(){
        WebElement dropDown = driver.findElement(By.id("pc_currency"));
        Select select = new Select(dropDown);
        select.selectByValue("EUR");
    }

    //9. “amount” kutusuna bir sayi girin
    @Test(priority = 6)
    public void amountsTest(){
        driver.findElement(By.id("pc_amount")).sendKeys("1000");
    }

    //10. “US Dollars” in secilmedigini test edin
    @Test(priority = 7)
    public void usDollarTest(){
        Assert.assertFalse(driver.findElement(By.id("pc_inDollars_true")).isSelected(),"US Dollars secili, Test FAILED!");
    }

    //11. “Selected currency” butonunu secin
    @Test(priority = 8)
    public void currencyTest(){
        driver.findElement(By.id("pc_inDollars_false")).click();
    }

    //12. “Calculate Costs” butonuna basin sonra “purchase” butonuna basin
    @Test(priority = 9)
    public void caculateCostsTest(){
        driver.findElement(By.id("pc_calculate_costs")).click();
        driver.findElement(By.id("purchase_cash")).click();
    }

    //13. “Foreign currency cash was successfully purchased.” yazisinin ciktigini kontrol edin.
    @Test(priority = 10)
    public void purchasedTest(){
        Assert.assertEquals(driver.findElement(By.id("alert_content")).getText(),"Foreign currency cash was successfully purchased.","Hatali mesaj alindi, Test FAILED!");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

    @BeforeMethod
    public void treatSleep() throws InterruptedException {
        Thread.sleep(2000);
    }

}
