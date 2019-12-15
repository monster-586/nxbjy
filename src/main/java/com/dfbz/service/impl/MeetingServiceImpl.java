package com.dfbz.service.impl;
import com.dfbz.dao.MeetingMapper;
import com.dfbz.entity.Meeting;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.MeetingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
public class MeetingServiceImpl extends tservisceIpm<Meeting> implements MeetingService {
@Autowired
MeetingMapper meetingMapper;
    @Override
    public PageInfo<Meeting> selectByCondition(Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Meeting> list= meetingMapper.selectByCondition(map);

        PageInfo<Meeting> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

}
