package com.dfbz.service;



import com.dfbz.entity.Meeting;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface MeetingService extends tservice<Meeting>{

    PageInfo<Meeting> selectByCondition(Map<String, Object> pam);


}