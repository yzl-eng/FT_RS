package com.project.service.impl;

import com.project.entity.Video;
import com.project.mapper.VideoMapper;
import com.project.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    public List<Video> findAll(){
        return videoMapper.selectList(null);
    }

}
