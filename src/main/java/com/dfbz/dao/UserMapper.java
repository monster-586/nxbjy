package com.dfbz.dao;

import com.dfbz.entity.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    @SelectProvider(type = SysUserSqlProvider.class, method = "selectByCondition")
    List<User> selectByCondition(Map<String, Object> pam);

    @Select("select count(1) focus from userfocus where user_id=#{uId}")
    int focusCount(Integer uId);

    @Select(" select us.* from userfocus uf " +
            " left join `user` us " +
            " on uf.user_focus_id=us.id " +
            " where uf.user_id=#{userId}")
    List<User> getFocus(Integer userId);

    @Delete(" delete from userfocus " +
            " where user_id=#{userId} " +
            " and user_focus_id=#{FocusUid} ")
    int deleteFocus(@Param("userId") Integer userId,@Param("FocusUid") Integer FocusUid);

    @Insert("INSERT INTO `userfocus`(`user_id`, `user_focus_id`) VALUES (#{userId}, #{FocusUid}) ")
    int insertFocus(@Param("userId") Integer userId, @Param("FocusUid") Integer FocusUid);
        }