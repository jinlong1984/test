package com.hiido.controllers.auth;

import com.hiido.entities.SysUser;
import com.hiido.repositories.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegisterController {
    @Autowired
    SysUserRepository sysUserRepository;

    @RequestMapping("/register")
    public SysUser register(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password,
                            @RequestParam(value = "password_confirmation", required = true) String password_confirmation) {
        if(!password.equals(password_confirmation)) {
            return null;
        }

        SysUser user = sysUserRepository.findByUsername(username);
        if(user != null) {
            return null;
        }

        user = new SysUser();
        user.setUsername(username);
        user.setPassword(password);
        user = sysUserRepository.save(user);
        return user;
    }
}
