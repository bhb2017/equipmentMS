package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.UpdateUser;
import com.lib.equipment.manager.dto.UserDTO;
import com.lib.equipment.manager.dto.UserData;
import com.lib.equipment.manager.dto.UserSearch;
import com.lib.equipment.manager.mapper.RoleMapper;
import com.lib.equipment.manager.mapper.UserMapper;
import com.lib.equipment.manager.mapper.UserRoleMapper;
import com.lib.equipment.manager.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    public User login(UserDTO userDTO) {
        UserExample userExample =new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userDTO.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()!=0){
            User user = users.get(0);
            if(userDTO.getPassword().equals(user.getPassword())){

                return user;
            }
        }
        return null;
    }

    public List<UserData> selectUserAndRole() {

        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        List<User> users = userMapper.selectByExample(example);

        List<UserData>userDataList= new ArrayList<>();
        for (User user : users) {
            UserData userData = new UserData();
            userData.setId(user.getId());
            userData.setPassword(user.getPassword());
            userData.setPhone(user.getPhone());
            userData.setStatus(user.getStatus());
            userData.setUsername(user.getUsername());
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserIdEqualTo(user.getId());
            List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
            List<Role> roles =new ArrayList<>();
            for (UserRole userRole : userRoles) {
                Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
                roles.add(role);
            }
            userData.setRoles(roles);
            userDataList.add(userData);
        }
        log.info("userDataList",userDataList);
        System.out.println(userDataList);
        return userDataList;
    }

    public List<Role> selectAllRole() {
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause("id desc");
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    public List<UserData> selectBySearch(UserSearch userSearch) {
       return null;
    }

    public List<User> selectAllUser() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("id desc");
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    public User selectUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(user!=null){
            return user;
        }else {
            return null;
        }
    }

    public List<Role> selectRoleByUser(User user) {
        if(user!=null){
            List<Role>roles = new ArrayList<>();
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserIdEqualTo(user.getId());
            List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
            for (UserRole userRole : userRoles) {
                Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
                roles.add(role);
            }
            return roles;
        }
        return null;
    }

    public void updateUserInfo(UpdateUser updateUser) {
        User user = new User();
        BeanUtils.copyProperties(updateUser,user);
        user.setId(updateUser.getId());

        userMapper.updateByPrimaryKeySelective(user);
        List<Integer> roles = updateUser.getRoles();
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        userRoleMapper.deleteByExample(userRoleExample);
        for (Integer rid: roles) {
            userRoleMapper.insertSelective(new UserRole(user.getId(),rid));
        }
    }
}
