package com.jybl.admin.controller;

import com.jybl.admin.entity.User;
import com.jybl.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    @RequestMapping("submitLogin")
    public Map login(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Integer rememberMe = Integer.valueOf(request.getParameter("rememberMe"));

        Map map = new HashMap();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe( rememberMe == 1 );
        Subject currentUser = SecurityUtils.getSubject();

        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            //logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            map.put("code", 0);
            //logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            //logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            map.put("code", 502);
            map.put("msg", "账户不存在");
            //redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            //logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            map.put("code", 503);
            map.put("msg", "密码不正确");
            //redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            //logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            map.put("code", 504);
            map.put("msg", "账户已锁定，请联系管理员");
            //redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            //logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            map.put("code", 505);
            map.put("msg", "密码错误次数过多");
            //redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            //logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            map.put("code", 506);
            map.put("msg", "操作失败，请稍后重试");
            ae.printStackTrace();
            //redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated() && (Integer)map.get("code") == 0) {

        } else {
            token.clear();
        }

        return map;
    }

    @RequestMapping("signOut")
    public Map userSignOut(HttpServletRequest request){

        Map resultMap = new HashMap();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("user/info")
    public Map getUserInfo(HttpServletRequest request){

        User user = null;

        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();
        if (null != collection && !collection.isEmpty()) {
            user = (User) collection.iterator().next();
        }

        Map map = new HashMap();

        user = userService.getByUsername(user.getUsername());

        map.put("code", 0);
        map.put("user", user);

        return map;
    }

    @RequestMapping("user/update")
    public Map updateUserInfo(HttpServletRequest request){

        Map map = new HashMap();
        User user = new User();

        user.setUsername(request.getParameter("username"));

        if (request.getParameter("alias") != null) {
            user.setAlias(request.getParameter("alias"));
        }

        if (request.getParameter("password") != null) {
            user.setPassword(request.getParameter("password"));
        }

        if (request.getParameter("avatar") != null) {
            user.setAvatar(request.getParameter("avatar"));
        }

        userService.updateUserInfo(user);

        map.put("code", 0);

        return map;
    }

    @RequestMapping("user/getSmsCode")
    public Map getSmsCode(HttpServletRequest request) {

        return userService.sendMsg(request);
    }

    @RequestMapping("user/resetPassword")
    public Map resetUserPassword(HttpServletRequest request) {
        return userService.resetPwd(request);
    }

}
