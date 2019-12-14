package com.dfbz.service;

import com.dfbz.entity.User;
import com.github.pagehelper.PageInfo;


import java.util.List;
import java.util.Map;

public interface UserService extends tservice<User> {



    PageInfo<User> selectByCondition(Map<String, Object> pam, Integer userId);

    User userDetail(Integer uId);

    int changeFocus(Integer userId, Integer focusUid);

    PageInfo<User> getFocus(Map<String, Object> map, Integer userId);
}
