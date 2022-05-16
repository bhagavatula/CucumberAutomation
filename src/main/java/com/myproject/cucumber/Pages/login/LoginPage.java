package com.myproject.cucumber.Pages.login;

import com.myproject.cucumber.vo.UserVo;

public interface LoginPage {
    void loginAs(UserVo uservo);
    void logOutAsSwtichuser(UserVo uservo);
}
