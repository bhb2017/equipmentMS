package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.UserDTO;
import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.service.UserService;
import com.lib.equipment.manager.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    /**
     * 登录
     * 用md5对密码加密
     * 登录交给shiro
     * @param userDTO
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(UserDTO userDTO, Model model,
                        HttpServletRequest request,
                        Integer rememberme){
        log.info("user:",userDTO);
        if((userDTO.getUsername()!=null&&!"".equals(userDTO.getUsername()))&&(userDTO.getPassword()!=null&&!"".equals(userDTO.getPassword()))){
            String newPassword= PasswordUtil.encodePwd(userDTO.getPassword());
            UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(),newPassword);
            Subject subject = SecurityUtils.getSubject();
            String user= (String) subject.getPrincipal();
            if(rememberme!=null&&rememberme==1){
                token.setRememberMe(true);

            }
            try {
                subject.login(token);

                request.getSession().setAttribute("user",userDTO.getUsername());
                return "university/index";
            }catch (Exception e){
                log.error("msg",e.getMessage());
                model.addAttribute("msg","用户名或密码错误");
                return "university/login";

            }
        }else {
            model.addAttribute("msg","用户名或密码不能为空");
            return "university/login";
        }



    }
}
