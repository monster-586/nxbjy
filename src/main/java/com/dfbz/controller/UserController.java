package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manager/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("list")
    @ResponseBody
    public PageInfo<User> selectByCondition(@RequestBody Map<String, Object> map, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println(userId);
        PageInfo<User> pageInfo = userService.selectByCondition(map, userId);
        return pageInfo;
    }

    @RequestMapping("userDetail")
    @ResponseBody
    public User userDetail(Integer uId) {
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
    @RequestMapping("changeFocus")
    @ResponseBody
    public Result selectByCondition(Integer FocusUid, HttpSession session) {
            System.out.println(FocusUid);
        Result result = new Result();
        result.setMsg("关注取消");
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println(userId);
        int i = userService.changeFocus(userId, FocusUid);
        if (i > 0) {
            result.setMsg("关注成功");
        }
        return result;

    }
    @RequestMapping("getFocus")
    @ResponseBody
    public PageInfo<User> getFocus(@RequestBody Map<String, Object> map,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        PageInfo<User> listFocus=userService.getFocus(map,userId);
        return listFocus;
    }
}
