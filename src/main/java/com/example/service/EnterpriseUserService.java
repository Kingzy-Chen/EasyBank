package com.example.service;

import com.example.domain.EnterpriseUser;


public interface EnterpriseUserService {

    public boolean createUser(EnterpriseUser user);
    public boolean checkSuperController(String username);
    public boolean deleteUsers(String enterprise);
    public boolean deleteUser(String username);

    public boolean changePassword(String username, String password);
    public String queryPassword(String username);
    public String queryEnterprise(String username);
    public String queryCtfId(String username);

    public boolean checkUsernameDuplicate(String username);
    public boolean checkEnterpriseDuplicate(String enterprise);
}
