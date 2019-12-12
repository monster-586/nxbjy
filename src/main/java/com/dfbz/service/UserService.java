package com.dfbz.service;

import com.dfbz.entity.User;
import com.github.pagehelper.PageInfo;


import java.util.Map;

public interface UserService extends tservice<User>{

   PageInfo<User> selectByCondition(Map<String, Object> pam);

   User userDetail(Integer uId);
}
