package com.myproject.cucumber.stepdefs;

import gherkin.formatter.model.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import utilities.CucumberLogUtils;
import utilities.ExcelUtils;
import utilities.QcTestResult;

import java.io.File;
import java.util.Map;
import java.util.Properties;

@Component
public class ScenarioContext {
    public static Properties localConf;
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal();
    public static ThreadLocal<Scenario> scenario = new ThreadLocal();
    public static ThreadLocal<String> dataFilePath = new ThreadLocal();
    public static ThreadLocal<String> sauceSessionId = new ThreadLocal();
    private static ThreadLocal<String> reportDirectory = new ThreadLocal();
    private static ThreadLocal<Boolean> reportFinished = new ThreadLocal();
    public static ThreadLocal<QcTestResult> currentQcResultTL = new ThreadLocal();
    public static final String SCENARIO_NAME_PROPERTY_NAMe = "scenarioName";
    public static final String USE_SCENARIO_NAME_PROPERTY = "userDefinedScenarioName";
    public static final String COMBINE_SCENARIO_OUTLINE_RESULTS_PROPERTYNAME = "combineScenarioOutlineOutput";

    private ScenarioContext() {
    }

    public static void setDateFile(String scenarioName, String dataDir) {
        String driverFile = dataDir + File.separator + "driver.xls";
        String excelColumnName = "scenarioName";
        scenarioName = scenarioName.trim();
        try {
            Map<String, String> dataValues = ExcelUtils.getRowDataByRowValue(new File(driverFile), excelColumnName, scenarioName);
            String dataFileColumnName = "dataFilePath";
            if (!dataValues.isEmpty()) {
                dataFilePath.set(dataDir + File.separator + (String) dataValues.get(dataFileColumnName));
            }
        } catch (Exception var6) {

        }
    }

    public static String getScenarioName() {
        String scenarioName = "";
        if (scenario.get() != null) {
            scenarioName = ((Scenario) scenario.get()).getName();
            CucumberLogUtils.logToConsole("Scenario Name:" + scenarioName);
        }
        return scenarioName;
    }

    @Deprecated
    public static String getScenarioUniqueID() {
        String scenarioID = "";
        if (scenario.get() != null) {
            CucumberLogUtils.logToConsole("Scenario Name:" + ((Scenario) scenario.get()).getName());
            scenarioID = ((Scenario) scenario.get()).getId();
            scenarioID = scenarioID.replaceAll("[^a-zA-Z0-9.-]", "_");
            CucumberLogUtils.logToConsole("Scenario ID:" + scenarioID);
        }
        return scenarioID;
    }

    @Deprecated
    public static QcTestResult getCurrentQcResult() {
        return (QcTestResult) currentQcResultTL.get();
    }

    @Deprecated
    public static void setCurrentQcResult(QcTestResult currentQcResult) {
        currentQcResultTL.set(currentQcResult);}
    @Deprecated
    public static void cleanCurrentQcResult()
    {
        currentQcResultTL.set((QcTestResult) null);
    }
    public static String getBrowserID(){
        return  localConf.getProperty("browser");
    }
    public static boolean isTakeScreenShots(){
        return "true".equals(localConf.getProperty("takeScreenShots"));
    }
    public static boolean isLogLevelDebug(){
        return "debug".equals(localConf.getProperty("logLevel"));
    }
    public static String getReportDirectory(){
        return (String)reportDirectory.get();
    }
    public static void setReportDirectory(String reportDirectory){
        ScenarioContext.reportDirectory.set(reportDirectory);
    }
    public static boolean isReportFinished(){
        return (Boolean)reportFinished.get();
    }

    public static void setReportFinished(boolean reportFinished){
        ScenarioContext.reportFinished.set(reportFinished);
    }


}


