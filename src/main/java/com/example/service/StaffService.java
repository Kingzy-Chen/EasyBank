package com.example.service;

import com.example.domain.Staff;


public interface StaffService {

    public boolean createStaff(Staff staff);
    public boolean deleteStaff(String username);
    public boolean changePassword(String username, String password);
    public String queryPassword(String username);
    public String queryType(String username);
    public String queryDepartment(String username);
}
