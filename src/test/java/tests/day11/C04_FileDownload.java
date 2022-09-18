package tests.day11;

import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDownload extends TestBase {
    @Test
    public void test() {
        //1. Tests packagenin altina bir class oluşturalim : C04_FileDownload
        // 2. Iki tane metod oluşturun : isExist() ve downloadTest()
        // 3. downloadTest () metodunun icinde aşağıdaki testi yapalim: - https://the-internet.herokuapp.com/download adresine gidelim. - code.txt dosyasını indirelim
        //4. Ardından isExist() methodunda dosyanın başarıyla indirilip indirilmediğini test edelim


    }

    @Test(dependsOnMethods = "downloadTest")
    public void isExist() {
        String dosyaYolu = System.getProperty("user.home")+"\\Downloads\\H1F3sQLG53.txt";
        //"C:\Users\User\Downloads\sample US.xlsx"
        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));
    }

    @Test
    public void downloadTest() {
        //https://the-internet.herokuapp.com/download adresine gidelim
        driver.get("https://the-internet.herokuapp.com/download");

        //- H1F3sQLG53.txt dosyasını indirelim
        driver.findElement(By.xpath("//a[text()='H1F3sQLG53.txt']")).click();


    }
}
