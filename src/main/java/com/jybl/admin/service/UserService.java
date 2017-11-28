package com.jybl.admin.service;

import com.jybl.admin.dao.UserMapper;
import com.jybl.admin.entity.User;
import com.jybl.admin.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SendMsgService sendMsgService;

    public User getByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public Integer updateUserInfo(User user) {
        return userMapper.updateUser(user);
    }

    private static final String KEY = "abc123"; // KEY为自定义秘钥

    /**
     * 获取验证码
     *
     * @param request
     * @return
     */
    public Map sendMsg(HttpServletRequest request) {

        Map map = new HashMap();

        try {
            String phone = request.getParameter("phone");
            if(userMapper.findByUsername(phone) == null){
                map.put("code", 501);
                map.put("msg", "帐号不存在");
                return map;
            }

            String randomNum = sendMsgService.createRandomNum(6);// 生成随机数
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 30);
            String currentTime = sf.format(c.getTime());// 生成30分钟后时间，用户校验是否过期

            if(sendMsgService.sendMsg(phone,randomNum) == "0"){ //此处执行发送短信验证码方法
                map.put("code", 508);
                map.put("msg", "验证码获取失败，请稍后再试");
                return map;
            }

            String hash = MD5Utils.getMD5Code(KEY + "@" + currentTime + "@" + randomNum);//生成MD5值
            // System.out.println(randomNum + " " + hash);
            map.put("code", 0);
            map.put("hash", hash);
            map.put("tamp", currentTime);
            return map; //将hash值和tamp时间返回给前端
        } catch (Exception e) {
            map.put("code", 509);
            map.put("msg", "验证码获取失败，请稍后再试");
            return map;
        }
    }

    /**
     * 效验验证码并重置密码
     * @param request
     * @return
     */
    public Map resetPwd(HttpServletRequest request) {

        Map map = new HashMap();

        try {
            String phone = request.getParameter("phone");
            String password = request.getParameter("password").toString();
            String requestHash = request.getParameter("hash").toString();
            String tamp = request.getParameter("tamp").toString();
            String msgNum = request.getParameter("msgNum").toString();
            String hash = MD5Utils.getMD5Code(KEY + "@" + tamp + "@" + msgNum);
            // System.out.println(msgNum + " " + hash);
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar c = Calendar.getInstance();
            String currentTime = sf.format(c.getTime());
            if (tamp.compareTo(currentTime) > 0) {  //判断是否过期
                if (hash.equalsIgnoreCase(requestHash)) {
                    //校验成功
                    User user = new User();
                    user.setUsername(phone);
                    user.setPassword(password);
                    userMapper.updateUser(user);
                    map.put("code", 0);
                    map.put("msg", "重置登录密码成功");
                    return map;
                } else {
                    //验证码不正确，校验失败
                    map.put("code", 506);
                    map.put("msg", "验证码不正确");
                    return map;
                }
            } else {
                // 超时过期
                map.put("code", 507);
                map.put("msg", "验证码已过期，请重新获取");
                return map;
            }
        } catch (Exception e) {
            map.put("code", 509);
            map.put("msg", "操作失败，请稍后重试");
            return map;
        }
    }

}
