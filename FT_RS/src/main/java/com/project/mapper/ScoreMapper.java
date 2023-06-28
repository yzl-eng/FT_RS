package com.project.mapper;

import com.project.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户评分表 Mapper 接口
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

}
