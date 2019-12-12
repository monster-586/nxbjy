package com.dfbz.controller;

import com.dfbz.Utils.Email;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("email")
public class emailController {
    @RequestMapping("send")
    @ResponseBody
    public Result send(String email, HttpSession session) {
        Result result = new Result();
        result.setMsg("邮箱不能为空！");
        if (!StringUtils.isEmpty(email)) {
            System.out.println(email);
            Integer code = (int) ((Math.random() * 9 + 1) * 1000);
            Email.send(email, code);
            session.setAttribute("emailCode", code);
            result.setMsg("发送成功！");
        }
        return result;
    }

    @Autowired
    UserService userService;

    @RequestMapping("updatePassword")
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
}
