package com.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
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
    @TableField(fill = FieldFill.INSERT) //创建时自动填充
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//创建与修改时自动填充
    private LocalDateTime updateTime;


}
