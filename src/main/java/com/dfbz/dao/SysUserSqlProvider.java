package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class SysUserSqlProvider {
    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from `user` " +
                " where 1=1 " );
        if(!StringUtils.isEmpty(map.get("realname"))){
            sb.append(" and real_name like concat('%',#{realname},'%') ");
        }
        return sb.toString();
    }

}
