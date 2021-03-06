package com.dfbz.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "dept")
public class Dept {
    /**
     * 部门id
     */
    @Id
    private Integer id;

    /**
     * 部门名称
     */
    private String name;
@Transient
    private Integer userCount;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    private List<User> listUser;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    /**
     * 获取部门id
     *
     * @return id - 部门id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置部门id
     *
     * @param id 部门id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名称
     *
     * @return name - 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userCount=" + userCount +
                ", listUser=" + listUser +
                '}';
    }
}