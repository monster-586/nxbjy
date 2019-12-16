package com.dfbz.service.impl;

import com.dfbz.dao.DeptMapper;
import com.dfbz.dao.MeetingMapper;
import com.dfbz.entity.Dept;
import com.dfbz.entity.Meeting;
import com.dfbz.entity.Result;
import com.dfbz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Service
public class DeptServiceImpl extends tservisceIpm<Dept> implements DeptService {
    @Autowired
    MeetingMapper meetingMapper;

    @Autowired
    DeptMapper deptMapper;

    @Override
    public Result insertDept(Map<String, Object> map) {
        Result result = new Result();
        result.setMsg("会议添加失败");
        System.out.println(map);
        Meeting meeting = new Meeting();
        if (!StringUtils.isEmpty(map.get("title"))) {
            meeting.setTitle((String) map.get("title"));
        }
        if (!StringUtils.isEmpty(map.get("deptId"))) {
            meeting.setDeptId((Integer)map.get("deptId"));
            Dept deptId = deptMapper.selectByPrimaryKey((Integer) map.get("deptId"));
            meeting.setDeptName(deptId.getName());
        }
        if (!StringUtils.isEmpty(map.get("makeUser"))) {
            meeting.setMakeUser((String) map.get("makeUser"));
        }
        if (!StringUtils.isEmpty(map.get("startTime"))) {
            try {
                String startTime =(String) map.get("startTime");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date=simpleDateFormat.parse(startTime);
                meeting.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(map.get("endTime"))) {
            try {
                String endTime =(String) map.get("endTime");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date=simpleDateFormat.parse(endTime);
                meeting.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(map.get("content"))) {
            meeting.setContent((String) map.get("content"));
        }
        meeting.setPublishDate(new Date());
        meeting.setStatus(0);

        int i = meetingMapper.insert(meeting);
        if(i>0){
            result.setMsg("会议添加成功");
        }
        return result;

    }
}
