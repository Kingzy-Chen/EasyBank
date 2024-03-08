package com.example.controller;

import com.example.domain.PersonalUser;
import com.example.service.PersonalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personalUsers")
public class PersonalUserController {

    @Autowired
    private PersonalUserService personalUserService;

    @PostMapping("/createUser")
    public Result createUser(@RequestBody PersonalUser user) {
        boolean flg = personalUserService.createUser(user);
        return new Result(flg, flg ? Code.SAVE_OK : Code.SAVE_ERR);
    }

    @PostMapping("/deleteUser/username={username}")
    public Result deleteUser(@PathVariable String username) {
        boolean flg = personalUserService.deleteUser(username);
        return new Result(flg, flg ? Code.DELETE_OK : Code.DELETE_ERR);
    }

    @PostMapping("/changePassword/username={username}&password={password}")
    public Result changePassword(@PathVariable String username, @PathVariable String password) {
        boolean flg = personalUserService.changePassword(username, password);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR, flg ? "密码修改成功！" : "密码修改失败！");
    }

    @PostMapping("/checkPassword/username={username}&password={password}")
    public Result checkPassword(@PathVariable String username, @PathVariable String password) {
        boolean flg = password.equals(personalUserService.queryPassword(username));
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "密码验证通过！" : "密码不正确！请重试！");
    }

    @PostMapping("/checkCtfId/username={username}&ctf_id={ctf_id}")
    public Result checkCtfId(@PathVariable String username, @PathVariable String ctf_id) {
        boolean flg = ctf_id.equals(personalUserService.queryCtfId(username));
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "" : "身份证验证失败！请重试！");
    }

    @PostMapping("/queryType/username={username}")
    public Result queryType(@PathVariable String username) {
        String type = personalUserService.queryType(username);
        return new Result(type, Code.GET_OK);
    }

    @PostMapping("/upgrade/username={username}")
    public Result upgrade(@PathVariable String username) {
        boolean flg = personalUserService.upgrade(username);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR);
    }

    @PostMapping("/degrade/username={username}")
    public Result degrade(@PathVariable String username) {
        boolean flg = personalUserService.degrade(username);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR);
    }
}
