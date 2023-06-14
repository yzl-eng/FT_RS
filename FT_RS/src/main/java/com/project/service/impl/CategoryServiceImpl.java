package com.project.service.impl;

import com.project.entity.Category;
import com.project.mapper.CategoryMapper;
import com.project.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影类型表 服务实现类
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
