package com.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.entity.News;
import com.project.entity.Result;
import com.project.entity.Video;
import com.project.entity.VideoCategory;
import com.project.service.impl.VideoCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping ("/add")
    public Result add(@RequestParam("categorys") ArrayList<Long> categorys,
                               @RequestParam Long videoId                ) {
        // 遍历数组，将每个值存入数据库
        for (Long category : categorys) {
            VideoCategory categoryObj = new VideoCategory();
            categoryObj.setCategoryId(category);
            categoryObj.setVideoId(videoId);
            videoCategoryService.save(categoryObj); // 保存category对象到数据库
        }
        System.out.println(categorys);
        return Result.success();
    }
    @GetMapping("/delete")
    public Result delete(@RequestParam Long videoId) {
        try {
            QueryWrapper<VideoCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("video_id",videoId);
            videoCategoryService.remove(wrapper);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }
    @GetMapping("/getCategory")
    public Result getCategory(@RequestParam Long videoId) {
        try {
            QueryWrapper<VideoCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("video_id",videoId);

            return Result.success( videoCategoryService.list(wrapper));
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }

}

