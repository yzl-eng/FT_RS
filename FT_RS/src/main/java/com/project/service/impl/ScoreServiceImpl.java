package com.project.service.impl;

import com.project.entity.Score;
import com.project.mapper.ScoreMapper;
import com.project.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户评分表 服务实现类
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

}
