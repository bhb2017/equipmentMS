package com.lib.equipment.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class testDemo {
    @Autowired
    HttpServletRequest request;
    @Test
    public void test() {
//        HttpServletRequest request;
        String contextPath = request.getServletPath();
        System.out.println(contextPath);
        return;
    }
}
