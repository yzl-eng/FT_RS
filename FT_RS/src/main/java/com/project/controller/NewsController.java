package com.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.News;
import com.project.entity.Result;
import com.project.entity.Video;
import com.project.mapper.NewsMapper;
import com.project.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    private NewsMapper newsMapper;

    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(newsService.list());
    }

    @PostMapping("/add")
    public Result add(@RequestBody News news) {
        try {
            newsService.save(news);
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加失败");
        }

    }
    @PostMapping("/delete")
    public Result delete(@RequestBody News news) {
        Long id = news.getId();
        try {
            newsService.removeById(id);
            System.out.println(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }
    @GetMapping("/queryData")
    @ResponseBody
    public Result queryData(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        // 当前页码和每页大小
        Page<News> page = new Page<>(pageNum, pageSize);
        // 查询条件
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        // 执行分页查询
        IPage<News> iPage = newsService.page(page);
        // 获取分页结果
        List<News> entityList = iPage.getRecords();
        long total = iPage.getTotal();
        // 返回分页结果
        return Result.success().data("records", entityList)
                .data("total", total);
    }
    @GetMapping("/findNum")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "4") Integer pageSize) {
        Page<News> page = new Page<>(pageNum, pageSize);
        IPage<News> newsList = newsMapper.selectPage(page, null);
        return Result.success(newsList.getRecords());
    }

    @PostMapping("/update")
    public Result update(@RequestBody News news) {
        newsService.updateById(news);
        return Result.success();
    }

}

