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
import org.springframework.web.servlet.ModelAndView;

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
        Object code = map.get("code");
        Object checkCode=session.getAttribute("checkCode");
        if (!StringUtils.isEmpty(map.get("code"))
                && code.equals(checkCode)) {
            User sysUser = new User();
            sysUser.setUsername((String) map.get("account"));
            sysUser.setPassword((String) map.get("password"));
//                sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password) + account));
            User loginUser = userService.selectOne(sysUser);
            if (loginUser != null) {
                result.setMsg("登录成功");
                result.setSysuser(loginUser);
                session.setAttribute("userId", loginUser.getId());
                if(map.get("remember").equals(true)){
                    session.setAttribute("loginUser", loginUser);
//                session.setAttribute(loginUser.getUsername(), loginUser);
                }

            } else {
                result.setMsg("账号或者密码错误");
            }
        } else {
            result.setMsg("验证码有误");
        }

        System.out.println("loginUser" + session.getAttribute("loginUser"));
        return result;
    }

    @RequestMapping("register")
    @ResponseBody
    public Result register(@RequestBody Map<String, Object> map) {
        Result result = new Result();
        User sysUser = new User();
        if (!StringUtils.isEmpty(map.get("account"))
                && !StringUtils.isEmpty(map.get("password"))
                && !StringUtils.isEmpty(map.get("email"))) {
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
    public Result check(@RequestBody Map<String, Object> map, HttpSession session) {
        Result result = new Result();
        Integer emailCode = (Integer) session.getAttribute("emailCode");
        String code = (String) map.get("code");
        if (!StringUtils.isEmpty(map.get("code"))
                && !StringUtils.isEmpty(map.get("password"))
                && !StringUtils.isEmpty(map.get("account"))
                && emailCode.equals(Integer.valueOf(code))
        ) {
            User loginUser = new User();
            loginUser.setUsername((String) map.get("account"));
            User checkUser = userService.selectOne(loginUser);
            if (checkUser != null && !checkUser.getPassword().equals(map.get("password"))) {
                checkUser.setPassword((String) map.get("password"));
                int i = userService.updateByPrimaryKey(checkUser);
                if (i > 0) {
                    result.setMsg("修改成功");
                }
            } else {
                result.setMsg("账号有误或与原密码相同");
            }
        } else {
            result.setMsg("请填写账号密码或验证码不正确");
        }
        return result;
    }

    @RequestMapping("sidebarUser")
    @ResponseBody
    public User getUser(HttpSession session) {
        Integer id = (Integer) session.getAttribute("userId");
        System.out.println("id:"+session.getAttribute("userId"));
        User loginUser = userService.selectByPrimaryKey(id);
        System.out.println("sidebar" + loginUser);
        return loginUser;
    }

    @RequestMapping("loginOut")
    @ResponseBody
    public ModelAndView loginOut(HttpSession session, ModelAndView modelAndView) {
//        Integer id = (Integer) session.getAttribute("userId");
//        User loginUser = userService.selectByPrimaryKey(id);
        ModelAndView mv = new ModelAndView("redirect:/static/index.html");
        return mv;
    }
}
