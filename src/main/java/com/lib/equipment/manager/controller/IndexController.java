package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.UserDTO;
import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String loginView(){
        return "university/login";
    }

    @GetMapping("/home")
    public String index(){
        return "university/index";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, Model model, HttpServletRequest request){
        System.out.println(userDTO);
        log.info("user:",userDTO);
        if(userDTO!=null){
            User login = userService.login(userDTO);
            if(login!=null){
                request.getSession().setAttribute("loginUser",login);
                if(userDTO.getRemember()!=null){
                    request.getSession().setAttribute("remUser",login);
                }
                return "university/index";
            }else {
                model.addAttribute("msg","用户名或密码错误");
                return  "university/login";
            }
        }else {
            model.addAttribute("msg","用户不存在");
            return  "university/login";
        }
    }
}
