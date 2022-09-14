package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class C03_Test {

    /*
        ● Bir class oluşturun: C3_DropDownAmazon
        ● https://www.amazon.com/ adresine gidin.
        - Test 1
           Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin

        -Test 2
        1. Kategori menusunden Books secenegini secin
        2. Arama kutusuna Java yazin ve aratin
        3. Bulunan sonuc sayisini yazdirin
        4. Sonucun Java kelimesini icerdigini test edin
     */

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void test01(){
    //Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin
        WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
        Select select = new Select(dropDown);
        List<WebElement> tumDropMenuList = select.getOptions();
        System.out.println("Tum kategori sayisi : "+tumDropMenuList.size());
        Assert.assertEquals(tumDropMenuList.size(),28,"Test FAILED drop menu 45'ten farkli");
    }

    @AfterClass
    public void tearDown(){
        //driver.close();
    }

    @Test
    public void test02(){
        WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
        Select select = new Select(dropDown);
       List<WebElement> tumOptionsList = select.getOptions();
        for (WebElement each:tumOptionsList
             ) {
            if (each.getText().equals("Books"))
            each.click();
        }

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java"+ Keys.ENTER);

        String resultsText = driver.findElement(By.xpath("(//div[@class='sg-col-inner'])[1]")).getText();
        System.out.println("Bulunan sonuc sayisi :");
        Arrays.stream(resultsText.split(" ")).limit(4).skip(3).forEach(System.out::println);

    }

}
