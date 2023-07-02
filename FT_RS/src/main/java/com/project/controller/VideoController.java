package com.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.News;
import com.project.entity.Result;
import com.project.entity.Video;
import com.project.mapper.VideoMapper;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private VideoMapper videoMapper;


    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(videoService.list());
    }

    @GetMapping("/findNum")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "4") Integer pageSize) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        IPage<Video> videoList = videoMapper.selectPage(page, null);
        return Result.success(videoList.getRecords());
    }
    @GetMapping("/findById")
    public Result findById(@RequestParam Long id){
       return Result.success( videoService.getById(id));
    }

    @PostMapping("/add")
    public Result addVideoInfo(@RequestBody Video video) {
        videoService.save(video);
        return Result.success(video);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody News news) {
        Long id = news.getId();
        try {
            videoService.removeById(id);
            System.out.println(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }

    @GetMapping("/queryData")
    @ResponseBody
    public Result queryData(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize,
                            @RequestParam(defaultValue = "") Integer typeId

    ) {
        // 当前页码和每页大小
        Page<Video> page = new Page<>(pageNum, pageSize);
        // 查询条件
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        if(typeId != null){
            wrapper.eq("type_id",typeId);
        }
//
        // 执行分页查询
        IPage<Video> iPage = videoService.page(page,wrapper);
        // 获取分页结果
        List<Video> entityList = iPage.getRecords();
        long total = iPage.getTotal();
        // 返回分页结果
        return Result.success().data("records", entityList)
                .data("total", total);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Video video) {
        videoService.updateById(video);
        return Result.success();
    }

}

