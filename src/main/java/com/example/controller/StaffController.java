package com.example.controller;

import com.example.domain.Staff;
import com.example.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/staffs")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/createStaff")
    public Result createUser(@RequestBody Staff staff) {
        boolean flg = staffService.createStaff(staff);
        return new Result(flg, flg ? Code.SAVE_OK : Code.SAVE_ERR);
    }

    @PostMapping("/deleteStaff/username={username}")
    public Result deleteUser(@PathVariable String username) {
        boolean flg = staffService.deleteStaff(username);
        return new Result(flg, flg ? Code.DELETE_OK : Code.DELETE_ERR);
    }

    @PostMapping("/changePassword/username={username}&password={password}")
    public Result changePassword(@PathVariable String username, @PathVariable String password) {
        boolean flg = staffService.changePassword(username, password);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR, flg ? "密码修改成功！" : "密码修改失败！");
    }

    @PostMapping("/checkPassword/username={username}&password={password}")
    public Result checkPassword(@PathVariable String username, @PathVariable String password) {
        boolean flg = password.equals(staffService.queryPassword(username));
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "密码验证通过！" : "密码不正确！请重试！");
    }

    @PostMapping("/queryType/username={username}")
    public Result queryType(@PathVariable String username) {
        String type = staffService.queryType(username);
        return new Result(type, Code.GET_OK);
    }

    @PostMapping("/queryDepartment/username={username}")
    public Result queryDepartment(@PathVariable String username) {
        String department = staffService.queryDepartment(username);
        return new Result(department, Code.GET_OK);
    }
}
