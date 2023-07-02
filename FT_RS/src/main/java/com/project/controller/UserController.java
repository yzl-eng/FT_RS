package com.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.entity.Result;
import com.project.entity.User;
import com.project.mapper.UserMapper;
import com.project.service.impl.UserServiceImpl;
import com.project.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result Login( @RequestBody Map<String, String> payload) {
            String username = payload.get("username");
            String password = payload.get("password");
        QueryWrapper<User> wrapper = new QueryWrapper<>();

            wrapper.eq("username",username);
            wrapper.eq("userpassword",password);
            User user=userMapper.selectOne(wrapper);
            if (user!=null) {
                String jwt= JwtUtil.sign(user);
                return Result.success().data("token",jwt).data("id",user.getId());
            }else {
                return Result.error("登录失败,用户名或密码");
            }
    }
    @GetMapping("/findById")
    public Result findById( @RequestParam Long id) {
        return Result.success(userService.getById(id));


    }

}

