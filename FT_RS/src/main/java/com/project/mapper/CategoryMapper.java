package com.project.mapper;

import com.project.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 电影类型表 Mapper 接口
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
