package com.dfbz.dao;

import com.dfbz.entity.User;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {
    @SelectProvider(type = SysUserSqlProvider.class, method = "selectByCondition")
    List<User> selectByCondition(Map<String, Object> pam);
}