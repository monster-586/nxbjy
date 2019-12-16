package com.dfbz.service;



import com.dfbz.entity.Dept;
import com.dfbz.entity.Result;

import java.util.Map;

public interface DeptService extends tservice<Dept>{

    Result insertDept(Map<String, Object> map);

}