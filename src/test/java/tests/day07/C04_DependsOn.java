package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

public class C04_DependsOn {

    /*
    amazon'a git
    test01 : ana sayfaya giitini test et
    test02 : 1.test basarili ise searchbox kullanarak "Nutella" icin arama yapin ve gerceklestigini test edin
    test03 : "Nutella" icin arama yapildi ise ilk urunu tiklayin ve fiyatinin ..... oldugunu test edin
     */

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void tearDown() {
        //driver.close();
    }

    @Test
    public void test01() {
        driver.get("https://www.amazon.com");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.com/", "url amazon degil!!!");

    }

    //Testleri birbirine bagladigimizda
    //1. 2. method'u tek basina calistirmak istesek bile otomatik olarak once 1. mthod calisir
    //2. 1.method failed olursa 2. method ignore edilir yani hic calistirilmaz
    @Test(dependsOnMethods = "test01")
    public void test02() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella", Keys.ENTER);
        String arananText = driver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText();
        arananText = arananText.replaceAll("\\W", "");
        Assert.assertEquals(arananText, "Nutella", "Istedigimiz arama yapilmadi!!!");
    }

    //eger 3 test veya daha fazlasi birbirine dependson ile baglandi ise
    // 3. yu calistirmak istedigimizde zincir reaksiyon calisip 1.ye gitmez
    @Test(dependsOnMethods = "test02")
    public void test03() {
        driver.findElement(By.xpath("(//span[@class='a-size-base a-color-base a-text-normal'])[1]")).click();
        String urunFiyat = driver.findElement(By.xpath("(//span[@class='a-size-mini olpWrapper'])[1]")).getText();
        Arrays.stream(urunFiyat.split(" ")).limit(4).skip(3).forEach(t-> Assert.assertEquals(t,"$55.09","Urun istenilen fiyatta degil!!!"));

        //Assert.assertEquals(urunFiyat,"$55.09","Urun istenilen fiyatta degil!!!");


    }


}
