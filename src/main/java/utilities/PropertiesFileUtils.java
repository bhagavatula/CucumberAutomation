package utilities;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Component
public class PropertiesFileUtils {
    public Properties properties = new Properties();

    public PropertiesFileUtils(String filePath){
        File inputFile = new File(filePath);
        try{
            FileInputStream fis = new FileInputStream(inputFile);
            this.properties.load(fis);
            MiscUtils.closeQuitely(new Closeable[]{fis});
        }catch (Exception var4){
            CucumberLogUtils.logError("Error loading properties files");
            var4.printStackTrace();
        }
    }
    public static void loadPropsFromResource(Properties properties, String configPath) throws IOException{
        if(StringUtils.isNotBlank(configPath)){
            InputStream inputStream = PropertiesFileUtils.class.getResourceAsStream(configPath);
            properties.load(inputStream);
        }else{
            throw new NullPointerException("properties config path can not be null");
        }
    }
}
