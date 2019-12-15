package com.dfbz.controller;


import com.dfbz.dao.DeptMapper;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.Dept;
import com.dfbz.entity.User;
import com.dfbz.service.MeetingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("manager/dept")
public class DeptController {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("list")
    @ResponseBody
    public PageInfo<Dept> selectActiveDept(@RequestBody Map<String, Object> map){
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Dept> depts = deptMapper.selectActiveDept();

        PageInfo<Dept> pageInfo = new PageInfo<>(depts);
        return pageInfo;

    }


    @RequestMapping("deptUser")
    @ResponseBody
    public List<User> selectDeptUser(Integer deptId){
        List<User> users = userMapper.selectDeptUser(deptId);
        return users;
    }
}
