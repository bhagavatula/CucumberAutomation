package com.myproject.cucumber;

import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Component;
import utilities.LocalConfUtils;

@Data
@Component
public class Userconfig {
//    @Value("$users.gmail.UserId")

    public String getgmailUserID(){
        return LocalConfUtils.getProperty("MAIN_USER_ID");

    };
    public String getgmailPassword(){
        return LocalConfUtils.getProperty("MAIN_USER_PWD");
    };


}
