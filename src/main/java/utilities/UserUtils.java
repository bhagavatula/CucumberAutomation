package utilities;


import com.myproject.cucumber.Pages.LoginPageStepsImpl;
import com.myproject.cucumber.Pages.login.LoginPage;
import com.myproject.cucumber.Userconfig;
import com.myproject.cucumber.enums.UserTypeEnum;
import com.myproject.cucumber.vo.UserVo;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UserUtils {

    private static Logger logger = Logger.getLogger(UserUtils.class.getName());
    private Userconfig userconfig;

    public UserUtils(Userconfig userconfig) {
        this.userconfig = userconfig;
    }


    public void initiateUserSession(LoginPage loginPage, UserTypeEnum userType, SessionIdentifier sessionIdentifier) {
        UserVo user = getUserVoCredentials(userType);
        loginPage.loginAs(user);
        sessionIdentifier.setSessionId(WebDriverUtil.extractSessionIdFromCookie());
    }

    public void ReinitiateUserSession(LoginPage loginPage, UserTypeEnum userType, SessionIdentifier sessionIdentifier) {
        UserVo user = getUserVoCredentials(userType);
        loginPage.logOutAsSwtichuser(user);
        sessionIdentifier.setSessionId(WebDriverUtil.extractSessionIdFromCookie());
    }


    public UserVo getUserVoCredentials(UserTypeEnum userType) {
        UserVo userVo = new UserVo();
        switch ((userType)) {
            case MAIN_USER_LOGIN:
                userVo.setUserId(EncryptDecryptUtil.decrypt(userconfig.getgmailUserID()));
                userVo.setPassword(EncryptDecryptUtil.decrypt(userconfig.getgmailPassword()));
                return userVo;
            default:
                logger.severe(String.format("User Type: %s doex not exist. Please add new user type to UserTypeEnums and in Usertuil.", userType));
                return userVo;
        }
    }


}




