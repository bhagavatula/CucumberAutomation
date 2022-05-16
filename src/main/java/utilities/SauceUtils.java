package utilities;


import com.saucelabs.saucerest.SauceREST;
import lombok.Value;
import org.springframework.stereotype.Component;
import com.myproject.cucumber.stepdefs.ThreadLocalContext;

import java.util.HashMap;
import java.util.Map;

import static com.myproject.cucumber.stepdefs.ScenarioContext.localConf;


@Component
public class SauceUtils {

    private static SauceREST sauceRest = null;

    public SauceUtils() {
    }

    private static SauceREST getSauceRest() {
        if (sauceRest == null) {
            String username = localConf.getProperty("sauceUserName");
            String accessKey = localConf.getProperty("sauceKey");
            sauceRest = new SauceREST(username, accessKey);
        }
        return sauceRest;
    }

    public static void updateResultOnSauceDashBoard(boolean isPass) {
        String sessionId = getSessionId();
        Map<String, Object> updates = getUpdatesHash(isPass);
        performUpdate(sessionId, updates);
    }

    public static void updateSauceDAshboard(HashMap<String, Object> updates) {
        String sessionId = getSessionId();
        performUpdate(sessionId, updates);
    }

    public static void performUpdate(String sessionId, Map<String, Object> updates) {
        SauceREST client = getSauceRest();
        if (sessionId != null && !sessionId.isEmpty()) {
            MiscUtils.setupHttpsProxy();
            client.updateJobInfo(sessionId, updates);
            MiscUtils.clearHttpsProxy();
        } else {
            CucumberLogUtils.logError("sauceSessionId is null or empty  ! Couldn't update Sauce dashboard");
        }
    }

    private static String getSessionId() {
        return (String) ThreadLocalContext.sauceSessionId.get();
    }

    private static Map<String, Object> getUpdatesHash(boolean testResult) {
        Map<String, Object> ret = new HashMap();
        ret.put("passed", testResult);
        return ret;
    }


}
