package com.project.controller;


import com.project.entity.Result;
import com.project.entity.VideoCategory;
import com.project.service.impl.VideoCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/videoCategory")
public class VideoCategoryController {

    @Autowired
    private VideoCategoryServiceImpl videoCategoryService;
    @PostMapping ("/add")
    public Result add(@RequestParam List<Long> categorys,
                               @RequestParam(defaultValue = "1") Long videoId                ) {
        // 遍历数组，将每个值存入数据库
        for (Long category : categorys) {
            VideoCategory categoryObj = new VideoCategory();
            categoryObj.setCategoryId(category);
            categoryObj.setVideoId(videoId);
            videoCategoryService.save(categoryObj); // 保存category对象到数据库
        }

        return Result.success();
    }

}

