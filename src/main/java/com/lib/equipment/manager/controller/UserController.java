package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.StatusMsg;
import com.lib.equipment.manager.dto.UpdateUser;
import com.lib.equipment.manager.dto.UserData;
import com.lib.equipment.manager.dto.UserSearch;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.model.Role;
import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 删除
     * 根据id删除
     * 前端ajax异步请求
     * @param user
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody User user){

        if(user.getId()!=null){
            boolean b = userService.deleteUser(user.getId());
            if(b){
                return new StatusMsg(1,"ok");
            }
        }
        return new StatusMsg(0,"fail");
    }

    /**
     * 添加用户
     * form表单校验@Valid
     * 校验规则看UpdateUser
     * @param addUser
     * @param result
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addUser(@Valid UpdateUser addUser, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result.getFieldError().getDefaultMessage());
        }
        addUser.setStatus(1);
        userService.insertUserAndRole(addUser);

        return "redirect:/user/list";
    }

    /**
     * 更新用户
     * @param updateUser
     * @return
     */
    @PostMapping("/update")
    public String update(UpdateUser updateUser){
        try {

            userService.updateUserInfo(updateUser);
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }

        return "redirect:/user/list";
    }

    /**
     * 与material/detail
     * 方法一样
     * @param id
     * @return
     */
    @PostMapping("/detail")
    @ResponseBody
    public UserData detail(@RequestBody @RequestParam("id") Integer id){
        ModelAndView mv =new ModelAndView();
        if(id!=null&&!"".equals(id)){
            User user= userService.selectUserById(id);
            List<Role>roles= userService.selectRoleByUser(user);
            try {
                UserData userData = new UserData();
                userData.setId(user.getId());
                userData.setUsername(user.getUsername());
                userData.setPhone(user.getPhone());
                userData.setPassword(user.getPassword());
                userData.setRoles(roles);
                return userData;
            }catch (Exception e){
                throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);

            }

        }
        mv.setViewName("university/users");
        return null;
    }

    /**
     *
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String userlist(@ModelAttribute("user")UpdateUser user, Model model){
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

    /**
     *搜索
     * @param userSearch
     * @return
     */
    @PostMapping("/search")
    public String search(UserSearch userSearch){

        List<UserData>users= userService.selectBySearch(userSearch);

        return "university/users";
    }
}
