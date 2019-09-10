package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.UpdateUser;
import com.lib.equipment.manager.dto.UserData;
import com.lib.equipment.manager.dto.UserSearch;
import com.lib.equipment.manager.model.Role;
import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping("/update")
    public String update(UpdateUser updateUser){
        System.out.println(updateUser);
        userService.updateUserInfo(updateUser);
        return "redirect:/user/list";
    }

    @PostMapping("/detail")
    @ResponseBody
    public UserData detail(@RequestBody @RequestParam("id") Integer id){
        ModelAndView mv =new ModelAndView();
        if(id!=null&&!"".equals(id)){
            User user= userService.selectUserById(id);
            List<Role>roles= userService.selectRoleByUser(user);
            UserData userData = new UserData();
            userData.setId(user.getId());
            userData.setUsername(user.getUsername());
            userData.setPhone(user.getPhone());
            userData.setPassword(user.getPassword());
            userData.setRoles(roles);
            return userData;
        }
        mv.setViewName("university/users");
        return null;
    }

    @GetMapping("/list")
    public String userlist(Model model){
        List<UserData> users= userService.selectUserAndRole();
        if(users!=null&&users.size()!=0){
            model.addAttribute("users",users);
        }
        List<Role>roles= userService.selectAllRole();
        model.addAttribute("roles",roles);
        List<User>us= userService.selectAllUser();
        model.addAttribute("sUsers",us);

        return "university/users";
    }

    @PostMapping("/search")
    public String search(UserSearch userSearch){
        System.out.println(userSearch);
        List<UserData>users= userService.selectBySearch(userSearch);

        return "university/users";
    }
}
