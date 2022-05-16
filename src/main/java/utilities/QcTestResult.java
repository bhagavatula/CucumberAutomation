package utilities;

import org.springframework.stereotype.Component;

@Component
public class QcTestResult {
    private final String scenarioName;
    private final String scenarioResult;
    private final String scenarioResultPath;

    public QcTestResult(String scenarioName, String scenarioResult, String scenarioResultPath){
        this.scenarioName = scenarioName;
        this.scenarioResult = scenarioResult;
        this.scenarioResultPath = scenarioResultPath;
    }

    public String getScenarioName(){
        return this.scenarioName;
    }
    public String getScenarioResult(){
        return  this.scenarioResult;
    }
    public String getScenarioResultPath(){
        return this.scenarioResultPath;
    }
}
