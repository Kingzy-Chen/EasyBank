package com.example.domain;


// 账户类
public class Account {
    private String id;  // 账号
    private String password;  // 密码
    private double balance;  // 余额
    private String type;  // 个人账户/企业账户: personal/enterprise
    private String deposit;  // 定期账户/活期账户: fixed/demand
    private String owner;  // 持有人/持有企业

    public Account(String id, String password, double balance, String type, String deposit, String owner) {
        this.id = id;
        this.password = password;
        this.balance = balance;
        this.type = type;
        this.deposit = deposit;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", deposit='" + deposit + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
