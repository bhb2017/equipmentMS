package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.UserDTO;
import com.lib.equipment.manager.mapper.UserMapper;
import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

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
}
