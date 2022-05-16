package com.myproject.cucumber.stepdefs.scenario;

import com.myproject.cucumber.Pages.LoginPageStepsImpl;
import com.myproject.cucumber.Pages.login.LoginPage;
import com.myproject.cucumber.enums.UserTypeEnum;
import com.myproject.cucumber.stepdefs.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utilities.SessionIdentifier;
import utilities.UserUtils;
@Component
public class GmailPageValidataion{
    @Autowired
    LoginPage loginPage;
    @Autowired
    UserUtils userUtils;

    LoginPageStepsImpl emailLoginPageStepsImpl = LoginPageStepsImpl.createInstance(ScenarioContext.webDriver.get());

    @Given("^user launching the gmail application$")
    public void user_launching_the_gmail_application() throws Throwable {
        emailLoginPageStepsImpl.navigateToPage();
    }

    @And ("user enter the user name and click next")
    public void username(){
        emailLoginPageStepsImpl.usernameInput();

    }

    @And ("user enter the password")
    public void usepassword(){

    }

    @When("user click on sigininButton")
    public void userclickonSignIButton(){

    }

    @Then("user validate home page is loading with \"([^\"]*)\"")
    public void uservalidatehomepageclickonSignIButto(String expectedMessage){

    }

    @And("^user enter the login credentials")
    public void userEnterCredentials() throws  Throwable{
        userUtils.initiateUserSession(loginPage, UserTypeEnum.MAIN_USER_LOGIN, new SessionIdentifier());
    }
}
