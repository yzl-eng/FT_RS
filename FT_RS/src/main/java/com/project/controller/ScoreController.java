package com.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.Result;
import com.project.entity.Score;
import com.project.entity.Video;
import com.project.service.impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户评分表 前端控制器
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreServiceImpl scoreService;
    @GetMapping("/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize,
                            @RequestParam Long id

    ) {
        // 当前页码和每页大小
        Page<Score> page = new Page<>(pageNum, pageSize);
        // 查询条件
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("video_id",id).orderByAsc("create_time");;
        System.out.println(wrapper.getSqlSegment());
        // 执行分页查询
        IPage<Score> iPage = scoreService.page(page,wrapper);
        // 获取分页结果
        List<Score> entityList = iPage.getRecords();
        long total = iPage.getTotal();
        // 返回分页结果
        return Result.success().data("records", entityList)
                .data("total", total);
    }
    @GetMapping("/find")
    public Result find() {
        // 当前页码和每页大小

        return Result.success(scoreService.list());
    }
    @PostMapping("/update")
    public Result update(@RequestBody Score score) {
        // 当前页码和每页大小

        return Result.success(scoreService.save(score));
    }

}

