package utilities;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import com.myproject.cucumber.stepdefs.ThreadLocalContext;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

import static com.myproject.cucumber.stepdefs.ThreadLocalContext.localconf;

@Component
public class ApplitoolsUtils {
    private static EyesRunner classicrunner;
    private static Eyes eyes;
    private static EyesRunner visualgridRunner;

    public static void setApplitoolsconfig() throws URISyntaxException{
        visualgridRunner = new VisualGridRunner();
        eyes = new Eyes(visualgridRunner);
        System.setProperty("jdk.tls.client.protocols","TLSv1.2");
        BatchInfo batch = new BatchInfo(localconf.getProperty("batchName"));
        batch.setNotifyOnCompletion(true);
        eyes.setBatch(batch);
        eyes.setApiKey(localconf.getProperty("applitoolsAPIKey"));
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.setServerUrl(localconf.getProperty("applitoolsServeURL"));
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setProxy(new ProxySettings("http://zsproxy.fanniemae.com"));
        eyes.setLogHandler(new StdoutLogHandler(false));
        Configuration config = eyes.getConfiguration();
        config.setAppName("EBQ");
        config.setTestName("EBQ Shakeout");

        config.addBrowser(1920,1080, BrowserType.CHROME)
                .addBrowser(1280,1024, BrowserType.CHROME)
                .addBrowser(1920,1080, BrowserType.FIREFOX)
                 .addBrowser(1280,1024, BrowserType.FIREFOX)
                 .addBrowser(1920,1080, BrowserType.EDGE_CHROMIUM)
                 .addBrowser(1280,1024, BrowserType.EDGE_CHROMIUM);
        eyes.setConfiguration(config);
    }

    public static Eyes getEyes(){
        return eyes;
    }

    public static void tearDown(){
        try{
            TestResults results = eyes.close(false);
            TestResultsSummary allTestResults = visualgridRunner.getAllTestResults(false);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            eyes.abortIfNotClosed();
        }
    }
}
