package com.dfbz.controller;

import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("manager/user")
public class UserController {
    @Autowired
   private UserService userService;


    @RequestMapping("list")
    @ResponseBody
    public PageInfo<User> selectByCondition(@RequestBody Map<String, Object> map) {

        PageInfo<User> pageInfo = userService.selectByCondition(map);
        System.out.println(pageInfo.getList());
        return pageInfo;
    }

    @RequestMapping("userDetail")
    @ResponseBody
        public User userDetail(Integer uId){
       return userService.userDetail(uId);
    }

//
//    @Value("${imgPath}")
//    String imgPath;
//    public User selectByPrimaryKey(Object key) {
//        User user = userService.selectByPrimaryKey(key);
//        // uploads/文件夹名称(企业id)/用户图片名/ File.separator 是系统默认的文件分隔符号
//        user.setPic(imgPath + user.getId() + File.separator + user.getPic());
//        return user;
//    }

}
