package com.dfbz.controller;


import com.dfbz.dao.DeptMapper;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.Dept;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.DeptService;
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

    @Autowired
    private DeptService deptService;

    @RequestMapping("list")
    @ResponseBody
    public PageInfo<Dept> selectActiveDept(@RequestBody Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Dept> depts = deptMapper.selectActiveDept();
        for (Dept dept : depts) {
            List<User> users = userMapper.selectDeptUser(dept.getId());
            dept.setListUser(users);
            dept.setUserCount(users.size());
        }
        PageInfo<Dept> pageInfo = new PageInfo<>(depts);
        return pageInfo;

    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Dept> selectALL() {
        return deptMapper.selectAll();

    }


    @RequestMapping("selectUser")
    @ResponseBody
    public List<User> selectUser(Integer deptId) {
        return userMapper.selectDeptUser(deptId);

    }

    @RequestMapping("save")
    @ResponseBody
    public Result selectUser(@RequestBody Map<String, Object> map) {
        Result result = new Result();
        Result result1 = deptService.insertDept(map);
        return result;
    }
}
