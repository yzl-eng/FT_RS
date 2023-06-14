package com.project.controller;


import com.project.entity.Result;
import com.project.entity.Video;
import com.project.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoServiceImpl videoService;

    @GetMapping("/findAll")
    public Result findAll(){
        return Result.success(videoService.list());
    }

    @PostMapping("/add")
    public Result addVideoInfo(@RequestBody Video video) {
        videoService.save(video);
        return Result.success();
    }
    @GetMapping("/findAllPage")
    public Result findAllPage(){
        return Result.success(videoService.list());
    }

}

