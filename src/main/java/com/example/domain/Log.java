package com.example.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


// 日志类
public class Log {
    private String username;  // 操作用户
    private String role;  // 用户身份
    private String op;  // 操作类型
    private String account_id;  // 操作账户号
    private double amount;  // 金额变动
    private String timestamp;  // 时间戳

    public Log(String username, String role, String op, String account_id, double amount) {
        this.username = username;
        this.role = role;
        this.op = op;
        this.account_id = account_id;
        this.amount = amount;

        // 时间戳自动生成
        SimpleDateFormat sdf = new SimpleDateFormat();
        this.timestamp = sdf.format(new Date());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Log{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", op='" + op + '\'' +
                ", account_id='" + account_id + '\'' +
                ", amount=" + amount +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
