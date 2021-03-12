package com.lib.equipment.manager.config;

import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.service.UserService;
import com.lib.equipment.manager.utils.PasswordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private UserService userService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //所需加密的参数  即  用户输入的密码
        String source = String.valueOf(utoken.getPassword());
        //[盐] 一般为用户名 或 随机数
        String salt = utoken.getUsername();
        //加密次数
        int hashIterations = 50;
        SimpleHash sh = new SimpleHash("md5", source, salt, hashIterations);
        String Strsh =sh.toHex();
        //打印最终结果
        String pwd = PasswordUtil.encodePwd(source);
        //获得数据库中的密码
        User dbUser = userService.findByUserName(((UsernamePasswordToken) token).getUsername());
        String dbPassword= (String) dbUser.getPassword();

        //进行密码的比对
        return source.equals(dbPassword);
    }

}
