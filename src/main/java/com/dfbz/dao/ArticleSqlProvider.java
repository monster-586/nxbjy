package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class ArticleSqlProvider {
    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from `article` " +
                " where 1=1 " );
        if(!StringUtils.isEmpty(map.get("title"))){
            sb.append(" and title like concat('%',#{title},'%') ");
        }
        return sb.toString();
    }

    public String getFavoriteByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT " +
                " ar.* " +
                "FROM " +
                " favorite fa " +
                " LEFT JOIN article ar ON ar.id = fa.a_id  " +
                "WHERE " +
                " fa.u_id = #{uId} ");
        if(!StringUtils.isEmpty(map.get("title"))){
            sb.append(" and title like concat('%',#{title},'%') ");
        }
        return sb.toString();
    }

}
