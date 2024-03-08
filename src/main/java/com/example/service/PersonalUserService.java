package com.example.service;

import com.example.domain.PersonalUser;


public interface PersonalUserService {

    public boolean createUser(PersonalUser user);
    public boolean deleteUser(String username);
    public boolean changePassword(String username, String password);
    public String queryPassword(String username);
    public String queryCtfId(String username);
    public String queryType(String username);

    public boolean upgrade(String username);
    public boolean degrade(String username);
}
