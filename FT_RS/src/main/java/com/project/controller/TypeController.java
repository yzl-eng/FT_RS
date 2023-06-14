package com.project.controller;


import com.project.entity.Result;
import com.project.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/findAll")
    public Result findAll(){
        return Result.success(typeService.list());
    }


}

