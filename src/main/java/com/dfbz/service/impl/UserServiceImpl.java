package com.dfbz.service.impl;

import com.dfbz.dao.UserMapper;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
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
        int focus = userMapper.focusCount(uId);
        User user = userMapper.selectByPrimaryKey(uId);
        user.setFocus(focus);
        return user;

    }

    @Override
    public int changeFocus(Integer userId, Integer focusUid) {
        List<User> focus = userMapper.getFocus(userId);
        int count = 0;
        for (User user : focus) {
            if (user.getId() != focusUid) {
                count++;
            }
        }
        if (count == focus.size()) {
            int i = userMapper.insertFocus(userId, focusUid);
            return i;
        } else {
            int i = userMapper.deleteFocus(userId, focusUid);
            return i;
        }
    }

    @Override
    public PageInfo<User> getFocus(Map<String, Object> map, Integer userId) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<User> listFocus = userMapper.getFocus(userId);
        PageInfo<User> pageInfo = new PageInfo<>(listFocus);
        return pageInfo;
    }


//    @Override
//    public List<User> getFocus(Integer userId) {
//        List<User> userList=userMapper.getFocus(userId);
//        return userList;
//    }
}
