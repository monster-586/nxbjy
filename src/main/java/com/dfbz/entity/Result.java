package com.dfbz.entity;

public class Result {
    private String msg;
    private User sysuser;
    private Integer type=0 ;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getSysuser() {
        return sysuser;
    }

    public void setSysuser(User sysuser) {
        this.sysuser = sysuser;
    }
}
