package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class MeetingSqlProvider {
    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select me.*  " +
                " from meeting me " +
                " left join meeting_join mj " +
                " on me.id=mj.c_id " +
                " where 1=1 ");


        if (!StringUtils.isEmpty(map.get("title"))) {
            sb.append(" and me.title like concat('%',#{title},'%') ");
        }
        if (!StringUtils.isEmpty(map.get("status"))) {
            sb.append(" and me.`status`=#{status} ");
        }
        return sb.toString();
    }
}
