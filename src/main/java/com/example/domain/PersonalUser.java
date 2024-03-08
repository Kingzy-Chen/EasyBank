package com.example.domain;


// 个人用户类
public class PersonalUser {
    private String username;  // 用户名
    private String password;  // 密码
    private String ctf_id;  // 身份证号
    private String type;  // 普通用户/VIP用户: common/VIP

    public PersonalUser(String username, String password, String ctf_id, String deposit, String type) {
        this.username = username;
        this.password = password;
        this.ctf_id = ctf_id;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PersonalUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ctf_id='" + ctf_id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
