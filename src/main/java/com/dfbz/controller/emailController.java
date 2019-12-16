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

            Integer code = (int) ((Math.random() * 9 + 1) * 1000);
            Email.send(email, code);
            session.setAttribute("emailCode", code);
            result.setMsg("发送成功！");
        }
        return result;
    }


}
