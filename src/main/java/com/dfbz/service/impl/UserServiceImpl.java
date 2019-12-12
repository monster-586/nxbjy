package com.dfbz.service.impl;

import com.dfbz.dao.UserMapper;
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
    public PageInfo<User> selectByCondition(Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<User> areas = userMapper.selectByCondition(map);
        PageInfo<User> pageInfo = new PageInfo<>(areas);
        return pageInfo;
    }

    @Override
    public User userDetail(Integer uId) {
        int focus = userMapper.focus(uId);
        User user = userMapper.selectByPrimaryKey(uId);
        user.setFocus(focus);
        return user;

    }
}
