package com.project.controller;


import com.project.entity.News;
import com.project.entity.Result;
import com.project.entity.Video;
import com.project.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(newsService.list());
    }

    @PostMapping("/add")
    public Result add(@ModelAttribute News news, @RequestParam(required = false) MultipartFile img) {
        try {
            newsService.save(news);
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加失败");
        }

    }


}

