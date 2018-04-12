package cn.wodesh.controller;

import cn.wodesh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/4/11.
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/rest/user/login")
    public Object login(@RequestParam String code , @RequestParam String token , @RequestParam String mac) throws Exception{
        return userService.login(code , token , mac);
    }


}
