package com.example.domain;


// 企业用户类
public class EnterpriseUser {
    private String username;  // 用户名
    private String password;  // 密码
    private String ctf_id;  // 身份证号
    private String enterprise; // 所属企业
    private boolean super_controller;  // 是否为超级用户

    public EnterpriseUser(String username, String password, String ctf_id, String deposit, String enterprise, boolean super_controller) {
        this.username = username;
        this.password = password;
        this.ctf_id = ctf_id;
        this.enterprise = enterprise;
        this.super_controller = super_controller;
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

    public String getCtf_id() {
        return ctf_id;
    }

    public void setCtf_id(String ctf_id) {
        this.ctf_id = ctf_id;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public boolean isSuper_controller() {
        return super_controller;
    }

    public void setSuper_controller(boolean super_controller) {
        this.super_controller = super_controller;
    }

    @Override
    public String toString() {
        return "EnterpriseUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ctf_id='" + ctf_id + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", super_controller=" + super_controller +
                '}';
    }
}
