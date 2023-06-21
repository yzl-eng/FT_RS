package com.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.Result;
import com.project.entity.Video;
import com.project.service.impl.VideoServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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
    public Result findAll() {
        return Result.success(videoService.list());
    }

    @PostMapping("/add")
    public Result addVideoInfo(@RequestBody Video video) {
        videoService.save(video);
        return Result.success();
    }

    @GetMapping("/findAllPage")
    public Result findAllPage() {
        return Result.success(videoService.list());
    }

    @GetMapping("/queryData")
    @ResponseBody
    public Result queryData(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        // 当前页码和每页大小
        Page<Video> page = new Page<>(pageNum, pageSize);
        // 查询条件
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        // 执行分页查询
        IPage<Video> iPage = videoService.page(page);
        // 获取分页结果
        List<Video> entityList = iPage.getRecords();
        long total = iPage.getTotal();
        // 返回分页结果
        return Result.success().data("records", entityList)
                .data("total", total);
    }

}

