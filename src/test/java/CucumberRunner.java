import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

//import cucumber.api.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import io.cucumber.junit.Cucumber;
//import org.junit.runner.RunWith;
//import cucumber.api.runner.Options.RunnerOptions;
//import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber.json",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
                "usage:target/cucumber-usage.json"},
        features = {"."},
        glue= {"com.myproject.cucumber.stepdefs"},
        tags={"@TestPositve","~@TestNagitive"},
        strict=false )

public class CucumberRunner extends AbstractTestNGCucumberTests {
//    private CucumberRunner(){}
//    @Override
//    @DataProvider
//    public Object[][] scenarios(){
//        return super.scenarios();
//    }

}


