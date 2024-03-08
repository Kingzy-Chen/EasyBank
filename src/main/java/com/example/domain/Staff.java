package com.example.domain;


// 银行雇员类
public class Staff {
    private String username;  // 用户名
    private String password;  // 密码
    private String type;  // 前台操作员/银行经理/业务总管/系统管理员: operator/manager/boss/admin
    private String department;  // 个人业务部门/企业业务部门: personal/enterprise

    public Staff(String username, String password, String type, String department) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
