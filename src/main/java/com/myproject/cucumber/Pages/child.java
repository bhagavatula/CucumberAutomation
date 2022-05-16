package com.myproject.cucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import utilities.LocalConfUtils;
import utilities.SauceWebDriver;

@Component
class child extends parent {


    String childname = nameVale;
    static WebDriver driver = null;

    public static void main(String[] args) {
//        parent hiparent = new parent();
//        hiparent.staticMethod(("testing",1);
//        hiparent.nonstaticMethod(("testing",1);
//        String childname1 =  hiparent.nameVale;
//       System.out.println(staticMethod("tseting",11));


       System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bala\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--test-type");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.get("www.google.com");
        driver.findElement(By.xpath(".//*[@name= 'q']")).sendKeys("hi");
        driver.findElement(By.xpath(".//*[@type = 'submit']")).click();
    }

    public static void entervalue(String entervale){
        driver.findElement(By.xpath(".//*[@name= 'q']")).sendKeys(entervale);
        driver.findElement(By.xpath(".//*[@type = 'submit']")).click();
    }

}
