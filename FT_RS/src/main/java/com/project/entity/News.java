package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻封面
     */
    private String img;

    /**
     * 新闻内容
     */
    private String context;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
