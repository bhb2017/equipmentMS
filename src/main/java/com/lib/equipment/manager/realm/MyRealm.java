package com.lib.equipment.manager.realm;

import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService usersService;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
//        Users user = userService.findByUserName(username);
//        user.setLocked(true);   //登录成功后锁定用户
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //根据用户名查找对应的角色集合
        authorizationInfo.setRoles(usersService.findRoles(user.getUsername()));
        //根据用户名查找对应的资源集合
        authorizationInfo.setStringPermissions(usersService.findPermissions(user.getUsername()));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();

        //根据用户名查找用户
        User user = usersService.findByUserName(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
