package com.loacg.web.service;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Project: blog4java
 * Author: Sendya <18x@loacg.com>
 * Time: 2016/3/16 12:52
 *
 * UtilName: 基础类，用于继承
 * Version: 1.0.0.1109
 */
public abstract class BaseService {

    public Map<String, Object> loginUser;

    @ModelAttribute
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.loginUser = (Map<String, Object>) request.getSession().getAttribute("loginUser");
        if(this.loginUser != null) {

        }
    }
}
