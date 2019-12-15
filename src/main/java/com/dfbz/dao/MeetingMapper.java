package com.dfbz.dao;


import com.dfbz.entity.Meeting;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface MeetingMapper extends Mapper<Meeting> {

    @SelectProvider(type = MeetingSqlProvider.class, method = "selectByCondition")
    List<Meeting> selectByCondition(Map<String, Object> map);




}