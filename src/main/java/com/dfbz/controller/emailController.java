package com.dfbz.controller;

import com.dfbz.Utils.Email;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/email")
public class emailController {
    @RequestMapping("check")
    public void email(@RequestBody String email, HttpSession session) {
        Integer code = (int) ((Math.random() * 9 + 1) * 1000);
        Email.send(email,code);
        session.setAttribute("emailCode",code);
    }

}
