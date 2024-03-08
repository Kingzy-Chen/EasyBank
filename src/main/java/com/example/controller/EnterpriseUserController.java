package com.example.controller;

import com.example.domain.EnterpriseUser;
import com.example.service.EnterpriseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enterpriseUsers")
public class EnterpriseUserController {

    @Autowired
    private EnterpriseUserService enterpriseUserService;

    @PostMapping("/createUser")
    public Result createUser(@RequestBody EnterpriseUser user) {
        boolean flg = enterpriseUserService.createUser(user);
        return new Result(flg, flg ? Code.SAVE_OK : Code.SAVE_ERR);
    }

    @PostMapping("/checkSuperController/username={username}")
    public Result checkSupperController(@PathVariable String username) {
        boolean flg = enterpriseUserService.checkSuperController(username);
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "" : "您不是超级管理员！无法操作！");
    }

    @PostMapping("/deleteUsers/enterprise={enterprise}")
    public Result deleteUsers(@PathVariable String enterprise) {
        boolean flg = enterpriseUserService.deleteUsers(enterprise);
        return new Result(flg, flg ? Code.DELETE_OK : Code.DELETE_ERR);
    }

    @PostMapping("/deleteUser/username={username}")
    public Result deleteUser(@PathVariable String username) {
        boolean flg = enterpriseUserService.deleteUser(username);
        return new Result(flg, flg ? Code.DELETE_OK : Code.DELETE_ERR);
    }

    @PostMapping("/changePassword/username={username}&password={password}")
    public Result changePassword(@PathVariable String username, @PathVariable String password) {
        boolean flg = enterpriseUserService.changePassword(username, password);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR, flg ? "密码修改成功！" : "密码修改失败！");
    }

    @PostMapping("/check/username={username}&password={password}&enterprise={enterprise}")
    public Result check(@PathVariable String username, @PathVariable String password, @PathVariable String enterprise) {
        boolean flg1 = password.equals(enterpriseUserService.queryPassword(username));
        boolean flg2 = enterprise.equals(enterpriseUserService.queryEnterprise(username));
        boolean flg = flg1 && flg2;
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "登录验证通过！" : "密码或企业名不正确！请重试！");
    }

    @PostMapping("/checkCtfId/username={username}&ctf_id={ctf_id}")
    public Result checkCtfId(@PathVariable String username, @PathVariable String ctf_id) {
        boolean flg = ctf_id.equals(enterpriseUserService.queryCtfId(username));
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "" : "身份证验证失败！请重试！");
    }

    @PostMapping("/checkUsernameDuplicate/username={username}")
    public Result checkUsernameDuplicate(@PathVariable String username) {
        boolean flg = enterpriseUserService.checkUsernameDuplicate(username);
        // flg = true 代表用户名已存在
        return new Result(flg, flg ? Code.GET_ERR : Code.GET_OK, flg ? "输入用户名已存在！请重试！" : "");
    }

    @PostMapping("/checkEnterpriseDuplicate/enterprise={enterprise}")
    public Result checkEnterpriseDuplicate(@PathVariable String enterprise) {
        boolean flg = enterpriseUserService.checkEnterpriseDuplicate(enterprise);
        // flg = true 代表企业名称已存在
        return new Result(flg, flg ? Code.GET_ERR : Code.GET_OK, flg ? "企业名称已被注册！请重试！" : "");
    }
}
