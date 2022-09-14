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
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class C02_DropDown {

    /*
    Bir class oluşturun: DropDown
    ● https://the-internet.herokuapp.com/dropdown adresine gidin.
    1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
    2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın
    3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
    4.Tüm dropdown değerleri(value) yazdırın
    5. Dropdown’un boyutunu bulun,
     Dropdown’da 4 öğe varsa konsolda True ,
     degilse False yazdırın.
     */

    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterClass
    public void tearDown(){
        //driver.close();
    }

    @Test
    public void tes01(){
        driver.get("https://the-internet.herokuapp.com/dropdown");
        //1. Adim : DrobDown'i locate ettik
        WebElement drobDown = driver.findElement(By.id("dropdown"));
        //2. Adim : Select class'i kullanarak bir obje olustur ve
        //parametre olarak locate ettigimiz webelementi yaz
        Select select = new Select(drobDown);
        // istedigin option'u select objesi kullanarak sec
       // 1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());

       // 2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın
        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());

        //3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());

        System.out.println("******************");
        // 4.Tüm dropdown değerleri(value) yazdırın
       List<WebElement> tumOpsiyonlar = select.getOptions();
       tumOpsiyonlar.stream().forEach(t-> System.out.println(t.getText()));

       // 5. Dropdown’un boyutunu bulun,Dropdown’da 4 öğe varsa konsolda True ,degilse False yazdırın.
        System.out.println(tumOpsiyonlar.size()>4 ? "True, $'den cok eleman var" : "False, 4'den az eleman var!!!");
        System.out.println("2. Yol");
        //Assert.assertTrue(tumOpsiyonlar.size()>4,"False, 4'den az eleman var!!!");




    }

}
