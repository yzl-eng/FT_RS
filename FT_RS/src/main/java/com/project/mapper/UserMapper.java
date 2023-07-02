package com.project.mapper;

import com.project.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
