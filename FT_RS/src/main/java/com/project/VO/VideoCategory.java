package com.project.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.project.entity.Video;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LZY
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoCategory implements Serializable {

    private Video video;


}
