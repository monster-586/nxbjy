package com.dfbz.dao;

import com.dfbz.entity.Dept;
import com.dfbz.entity.Result;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DeptMapper extends Mapper<Dept> {
    @Select(" SELECT de.* from dept de " +
            " where de.id in" +
            "( select us.dept_id from `user` us)")
    List<Dept> selectActiveDept();


}