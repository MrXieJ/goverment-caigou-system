package com.caigou.admin.security;

import com.caigou.admin.entity.User;
import com.caigou.admin.service.RoleService;
import com.caigou.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class ShiroRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("##################执行Shiro权限认证##################");
        User user = (User) principalCollection.getPrimaryPrincipal();
        if(user!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("用户验证执行 : "+token.getUsername());
        User user = userService.getByUsername(token.getUsername());
        if(user==null){
            logger.error("用户 { "+token.getUsername()+" } 不存在 ");
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getFreeze()==1){
            logger.error("用户 { "+token.getUsername()+" } 被禁止登录 ");
            throw new DisabledAccountException("账号已经禁止登录");
        }else{
            //user.setUpdated(DateUtils.getNowTimestamp());
            //user.setUpdatedAt(DateUtils.getNowFormatDate(null));
            //System.out.println("效验更新前ROLE："+user.getRole().getRId());
            //userService.update(user,true,user.getId());
        }
        logger.info("用户 { "+token.getUsername()+" } 通过 ");
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }


}
