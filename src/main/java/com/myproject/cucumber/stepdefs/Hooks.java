package com.myproject.cucumber.stepdefs;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import utilities.CucumberLogUtils;
import utilities.LocalConfUtils;
import utilities.SauceUtils;
import utilities.SauceWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.myproject.cucumber.stepdefs.ScenarioContext.localConf;

@Component
public class Hooks {
    @Before(order = 0)
    public void initializeDriver(Scenario s) throws IOException, URISyntaxException {
        WebDriver driver = null;
        com.myproject.cucumber.stepdefs.ThreadLocalContext.localconf = LocalConfUtils.loadLocalConf();
        com.myproject.cucumber.stepdefs.ThreadLocalContext.setScenario(s);
        CucumberLogUtils.scenarioResult = true;
        System.out.println(LocalConfUtils.getProperty("localchromeDriverLocation"));
//        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.chrome.driver", LocalConfUtils.getProperty("localchromeDriverLocation"));
        if (LocalConfUtils.getProperty("seleniumHub").contentEquals("saucelabs")) {
            driver = SauceWebDriver.getWebDriver();
        } else {
            ChromeOptions options = new ChromeOptions();
//            options.addExtensions(new File(LocalConfUtils.getProperty("extensionLocaltion key name mentioned in localconf file")));
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--test-type");
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
            //below is for selenidew
//            WebDriverRunner.setWebDriver(new ChromeDriver(options));


        }
        //below is for selenium
        driver.manage().window().maximize();
        com.myproject.cucumber.stepdefs.ScenarioContext.webDriver.set(driver);
        //below is for selenide
//      WebDriverRunner.getWebDriver().manage().window().maximize();
//        com.myproject.cucumber.stepdefs.ScenarioContext.webDriver.set(driver);
//        if(ApplitoolsUtils.getEyes() == null){
//            ApplitoolsUtiils.setApplitoolsConfig();
//        }

    }

    @After
    public static void tearDown() {
        if (localConf.getProperty("seleniumHub").contentEquals("saucelabs")) {
            try {
                SauceUtils.updateResultOnSauceDashBoard(!com.myproject.cucumber.stepdefs.ThreadLocalContext.Scenario.get().isFailed());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (com.myproject.cucumber.stepdefs.ScenarioContext.webDriver.get() != null) {
            com.myproject.cucumber.stepdefs.ScenarioContext.webDriver.get().quit();
            com.myproject.cucumber.stepdefs.ScenarioContext.webDriver.remove();
        }
        com.myproject.cucumber.stepdefs.ScenarioContext.webDriver.set(null);
    }

}
