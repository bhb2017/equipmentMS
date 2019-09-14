package com.lib.equipment.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
@RequestMapping("/calculate")
public class CalculateController {
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String index(){
        return "university/holiday";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String getData(@RequestParam("forecasttime") Date date){

        long time = date.getTime();
        java.sql.Date sdate = new java.sql.Date(time);

        return "university/holiday";
    }
}
