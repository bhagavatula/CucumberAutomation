package com.myproject.cucumber.stepdefs;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import utilities.LocalConfUtils;

import java.util.Properties;
@Component
public class ThreadLocalContext {
    public static Properties localconf;
    public static final ThreadLocal<cucumber.api.Scenario> Scenario = new ThreadLocal();
    public static final ThreadLocal<String> ScenarioName = new ThreadLocal();
    public static final ThreadLocal<Long> globalTimeout = new ThreadLocal();
    public static final ThreadLocal<Long>globalPolling = new ThreadLocal();
    public static final ThreadLocal<Integer> RETRY_ATTEMPT = new ThreadLocal();
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    public static ThreadLocal<String> sauceSessionId  = new ThreadLocal();

    public ThreadLocalContext(){
    }

    public static String getBrowserID(){
        return localconf.getProperty("browser");
    }
    public static boolean isTakenScreenShot(){
        return "true".equals(localconf.getProperty("takeScreenShots"));
    }
    public static boolean isLogLevelDebug(){
        return "debug".equals(localconf.getProperty("logLevel"));
    }
    public static void setGlobalTimeout(Long timeout){
        globalTimeout.set(timeout);
    }
    public static long getGlobalTimeOut(){
        Long returnValue = null;
        if(globalTimeout.get() != null){
            returnValue = (Long) globalTimeout.get();
        }
        return returnValue;
    }
    public static void setGlobalPolling(Long polling)
    {
        globalPolling.set(polling);
    }
    public static long getGlobalPolling(){
        Long returnvalue = null;
        if(globalPolling.get() != null){
            returnvalue = (Long) globalPolling.get();
        }
        return returnvalue;
    }

    public static void setRetry(Integer retryAttempt){
        RETRY_ATTEMPT.set(retryAttempt);
    }
    public static void setScenario(Scenario scenario){
        Scenario.set(scenario);
    }
    public static Integer getRetryAttempt(){
        Integer returnValue = null;
        if(RETRY_ATTEMPT.get() != null){
            returnValue = (Integer) RETRY_ATTEMPT.get();
        }
        return returnValue;
    }

    public static boolean isTakeScreenShots(){
        return "true".equals((LocalConfUtils.getProperty("takeScreenShots")));
    }
    public static void setScenarioName(String scenarioName) {
        ScenarioName.set(scenarioName);
    }
    public static String getScenarioName(){
        String returnValue = null;
        if (ScenarioName.get()!=null){
            returnValue= (String)ScenarioName.get();
        }
        return returnValue;
    }


}
