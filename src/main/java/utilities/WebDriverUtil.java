package utilities;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.myproject.cucumber.stepdefs.ThreadLocalContext;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Component
public class WebDriverUtil {

    public WebDriverUtil(){
    }

    public static byte[] getScreenShot(){
        byte[] screenshot = null;
        WebDriver driver = (WebDriver) ThreadLocalContext.webDriver.get();
        try{
            screenshot = (byte[]) ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        }catch(Exception var3){
            CucumberLogUtils.logError("Couldn't take screenshot");
        }
        return screenshot;
    }

    public static String extractSessionIdFromCookie(){
        Set<Cookie> allCookies = getWebDriver().manage().getCookies();
        String sessionId = "";
        for(Cookie loaddedCookie : allCookies){
            if("JSESSIONID".equals(loaddedCookie.getName())){
                sessionId = loaddedCookie.getValue();
                break;
            }
        }
        return sessionId;
    }
}
