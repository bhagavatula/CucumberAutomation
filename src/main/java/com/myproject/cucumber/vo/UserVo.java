package com.myproject.cucumber.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserVo {
    private String userId;
    private String password;
}
