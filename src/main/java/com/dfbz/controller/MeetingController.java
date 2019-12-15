package com.dfbz.controller;

import com.dfbz.entity.Article;
import com.dfbz.entity.Meeting;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.ArticleService;
import com.dfbz.service.MeetingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manager/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @RequestMapping("list")
    @ResponseBody
    public PageInfo<Meeting> selectByCondition(@RequestBody Map<String, Object> map) {
        PageInfo<Meeting> pageInfo = meetingService.selectByCondition(map);
        return pageInfo;
    }

}
