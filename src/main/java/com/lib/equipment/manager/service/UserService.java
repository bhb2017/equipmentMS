package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.UpdateUser;
import com.lib.equipment.manager.dto.UserDTO;
import com.lib.equipment.manager.dto.UserData;
import com.lib.equipment.manager.dto.UserSearch;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.form.AddUser;
import com.lib.equipment.manager.mapper.*;
import com.lib.equipment.manager.model.*;
import com.lib.equipment.manager.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

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
            userData.setRoles(roles.get(0));
            userDataList.add(userData);
        }
        log.info("userDataList：{}",userDataList);

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
        user.setPassword(PasswordUtil.encodePwd(updateUser.getPassword()));
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i!=0) {
            Integer role= updateUser.getRole();
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserIdEqualTo(user.getId());
            int i1 = userRoleMapper.deleteByExample(userRoleExample);
            userRoleMapper.insertSelective(new UserRole(user.getId(),role));
//            for (Integer rid : roles) {
//                userRoleMapper.insertSelective(new UserRole(user.getId(), rid));
//            }

        }else {
            log.error("更新用户错误:");
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }

    }

    public void insertUserAndRole(AddUser addUser) {
        User user = new User();
        BeanUtils.copyProperties(addUser,user);
        user.setPassword(PasswordUtil.encodePwd(user.getPassword()));
        userMapper.insert(user);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null){
            User u = users.get(0);
            if(addUser.getRole()!=null){
                userRoleMapper.insertSelective(new UserRole(u.getId(),addUser.getRole()));
//                for (Integer role : addUser.getRoles()) {
//                    System.out.println(role);
//                    userRoleMapper.insertSelective(new UserRole(u.getId(),role));
//                }
            }
        }
    }

    public boolean deleteUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i!=0){
            return true;
        }
        return false;
    }

    public User findByUserName(String username) {
//        List<User> list = userMapper.findByUserName(username);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);
        if(list.size()<1){
            throw  new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }else{
            return list.get(0);
        }
    }

    //根据用户名查找用户角色
    public Set<String> findRoles(String username){
        Set<String> roleNameSet = new HashSet<>();
        Integer userId = findByUserName(username).getId();
//        List<UserRole> usersRoleList = UserRoleExample.findByUserId(userId);
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserRole> usersRoleList = userRoleMapper.selectByExample(userRoleExample);
        for(int i = 0; i < usersRoleList.size(); i++){
            Role role = roleMapper.selectByPrimaryKey(usersRoleList.get(i).getRoleId());
            roleNameSet.add(role.getRoleName());
        }
        return roleNameSet;
    }
    //根据用户名查找用户权限
    public Set<String> findPermissions(String username){
        Set<String> permissionNameSet = new HashSet<>();
        Integer userId = findByUserName(username).getId();
        List<Integer> roleIdList = new ArrayList<>();
//        List<UserRole> usersRoleList = usersRoleDao.findByUserId(userId);
        UserRoleExample userRoleExample =new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserRole> usersRoleList = userRoleMapper.selectByExample(userRoleExample);
        for(int i = 0; i < usersRoleList.size(); i++){
            Integer roleId = roleMapper.selectByPrimaryKey(usersRoleList.get(i).getRoleId()).getId();
            roleIdList.add(roleId);
        }
//        List<RolePermission> rolePermissionList = rolePermissionMapper.findByRoleIdIn(roleIdList);
        RolePermissionExample rolePermissionExample =new RolePermissionExample();
        RolePermissionExample.Criteria criteria1 = rolePermissionExample.createCriteria();
        criteria1.andRoleIdIn(roleIdList);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        for(int i = 0;i<rolePermissionList.size();i++){
            Permission permission = permissionMapper.selectByPrimaryKey(rolePermissionList.get(i).getPermissionId());
            permissionNameSet.add(permission.getPermissionName());
        }
        return permissionNameSet;
    }
}
