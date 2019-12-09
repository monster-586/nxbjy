package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class loginController {
    @Autowired
    UserService userService;

    @RequestMapping("check")
    @ResponseBody
    public Result checkAccount(@RequestBody Map<String, Object> map, HttpSession session) {
        Result result = new Result();
        if (!StringUtils.isEmpty(map.get("code"))) {
            Object code = map.get("code");
            Object checkCode = session.getAttribute("checkCode");
            if (checkCode.equals(code)) {
                User sysUser = new User();
                String account = (String) map.get("account");
                String password = (String) map.get("password");
                sysUser.setUsername(account);
                sysUser.setPassword(password);
//                sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password) + account));
                User loginUser = userService.selectOne(sysUser);
                if (loginUser != null) {
                    result.setMsg("登录成功");
                    result.setSysuser(loginUser);
                } else {
                    result.setMsg("账号或者密码错误");
                }
            } else {
                result.setMsg("验证码错误");
            }
        } else {
            result.setMsg("验证码不能为空");
        }

        return result;
    }

    @RequestMapping("register")
    @ResponseBody
    public Result register(@RequestBody Map<String, Object> map) {
        Result result = new Result();
        User sysUser = new User();
        if (!StringUtils.isEmpty(map.get("account")) && !StringUtils.isEmpty(map.get("password")) && !StringUtils.isEmpty(map.get("email"))) {
            String account = (String) map.get("account");
            sysUser.setUsername(account);
//                sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password) + account));
            User registerUser = userService.selectOne(sysUser);
            if (registerUser == null) {
                String password = (String) map.get("password");
                String email = (String) map.get("email");
                sysUser.setPassword(password);
                sysUser.setEmail(email);
                int i = userService.insertSelective(sysUser);
                result.setType(1);
                result.setMsg("注册成功");
            } else {
                result.setMsg("账户已存在");
            }
        } else {
            result.setMsg("未填写");
        }
        return result;
    }

    @RequestMapping("forget")
    @ResponseBody
    public Result forget(@RequestBody Map<String, Object> map, HttpSession session) {

        if (!StringUtils.isEmpty(session.getAttribute("emailCode")) && !StringUtils.isEmpty(session.getAttribute("password"))
                && !StringUtils.isEmpty(session.getAttribute("comfpassword"))) {
            if (session.getAttribute("emailCode").equals(map.get("emailCode"))) {
            }
        }
        return null;
    }


}
