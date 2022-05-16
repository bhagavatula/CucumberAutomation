package com.myproject.cucumber.Pages;

import com.myproject.cucumber.stepdefs.ScenarioContext;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import utilities.EnvUtil;

import java.util.List;

@Component
public class LoginPageStepsImpl {

    public static String name = "test";

//    @FindBy(how = How.ID, using= "username")
//    WebElement username;
//    @FindBy(how = How.XPATH, using = "//button[text()='submit']")
//    WebElement submitbtn;

    private By gmailuserName = By.id("identifierId");
    private By gmailNextButton = By.xpath("//*[text()='Next']");



    public static LoginPageStepsImpl createInstance(WebDriver webDriver){
        LoginPageStepsImpl emailloginpage = PageFactory.initElements(webDriver, LoginPageStepsImpl.class);
        return emailloginpage;
    }

    public void navigateToPage(){
        System.out.println(EnvUtil.getAutomationUrl());

        ScenarioContext.webDriver.get().get(EnvUtil.getAutomationUrl());
    }
    public void usernameInput(){
        ScenarioContext.webDriver.get().findElement(gmailuserName).sendKeys("iambalabh@gmail.com");
        ScenarioContext.webDriver.get().findElement(gmailNextButton).click();

    }
    public void passwordInput(String value){
        ScenarioContext.webDriver.get().findElement(By.id("identifierId")).sendKeys(value);

    }

    }
