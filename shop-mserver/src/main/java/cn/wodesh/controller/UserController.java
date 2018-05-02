package cn.wodesh.controller;

import cn.wodesh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${login_test}")
    private boolean login_test;

    @GetMapping("/rest/user/login")
    public Object login(@RequestParam String code , @RequestParam String token) throws Exception{
        return login_test ? userService.loginTest(code , token) : userService.login(code , token);
    }


}
