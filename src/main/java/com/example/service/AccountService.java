package com.example.service;

import com.example.domain.Account;


public interface AccountService {

    public boolean createAccount(Account account);
    public boolean depositToAccount(String id, double amount);
    public boolean withdrawFromAccount(String id, double amount);
    public boolean transfer(String from_id, String to_id, double amount);
    public double queryBalance(String id);
    public boolean changePassword(String id, String password);
    public String queryPassword(String id);
    public String queryOwner(String id);
    public String queryType(String id);
    public boolean deleteAccount(String id);
}
