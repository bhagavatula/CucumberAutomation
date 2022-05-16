package utilities;

import org.springframework.stereotype.Component;

import java.io.File;


@Component
public class EnvUtil {
    private static XMLUtils getEnv(){
        String env =  LocalConfUtils.getProperty("env");
        String envFilePath = LocalConfUtils.getResourceDir()+ File.separator+ "test" +File.separator+env+".xml";
        File envFile = new File(envFilePath);
        XMLUtils xml = new XMLUtils(envFile);
        return xml;
    }
    public static String getAutomationUrl(){
        String ret = "";
        XMLUtils xml = getEnv();
        ret = xml.get_data(("//application[@id='AUTOMATIONURL']/url"));
        return ret;
    }
}
