package com.dfbz.service.impl;

import com.dfbz.dao.DeptMapper;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.Dept;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends tservisceIpm<User> implements UserService {
    @Autowired
    UserMapper userMapper;



    @Override
    public PageInfo<User> selectByCondition(Map<String, Object> map, Integer userId) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<User> listUser = userMapper.selectByCondition(map);
        List<User> listFocus = userMapper.getFocus(userId);

        for (User user : listUser) {
            for (User focus : listFocus) {
                if (user.getId() == (focus.getId())) {
                    user.setFocus(1);
                }
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(listUser);
        return pageInfo;
    }

    @Override
    public User userDetail(Integer uId) {
        int focusCount = userMapper.focusCount(uId);
        User user = userMapper.selectByPrimaryKey(uId);
        user.setFocusCount(focusCount);
        return user;

    }

    @Override
    public Result changeFocus(Integer userId, Integer focusUid) {
        Result result = new Result();
        List<User> focus = userMapper.getFocus(userId);
        int count = 0;
        for (User user : focus) {
            if (user.getId() != focusUid) {
                count++;
            }
        }
        if (count == focus.size()&&userId!=focusUid) {
            int i = userMapper.insertFocus(userId, focusUid);
            result.setMsg("关注成功");
        } else if(count != focus.size()){
            int i = userMapper.deleteFocus(userId, focusUid);
           result.setMsg("取消关注");
        }else {
            result.setMsg("不能关注自己");
        }
        return result;
    }

    @Override
    public PageInfo<User> getFocus(Map<String, Object> map, Integer userId) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<User> listFocus = userMapper.getFocus(userId);
        PageInfo<User> pageInfo = new PageInfo<>(listFocus);
        return pageInfo;
    }
@Autowired
    DeptMapper deptMapper;
    @Override
    public Result updateUser(User user) {
        Result result = new Result();
        result.setMsg("保存失败");
        if("true".equals(user.getIsSecret())){
            user.setIsSecret("0");
        }else {
            user.setIsSecret("1");
        }
        Dept dept = deptMapper.selectByPrimaryKey(user.getDeptId());
        user.setDeptName(dept.getName());
        int i = userMapper.updateByPrimaryKey(user);
        if (i>0){
            result.setMsg("保存成功");
        }
        return result;
    }


//    @Override
//    public List<User> getFocus(Integer userId) {
//        List<User> userList=userMapper.getFocus(userId);
//        return userList;
//    }
}
