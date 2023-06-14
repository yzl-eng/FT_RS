package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 影视名
     */
    @TableField("Name")
    private String name;

    /**
     * 影视翻译名
     */
    @TableField("T_Name")
    private String tName;

    /**
     * 影视海报
     */
    private String img;

    /**
     * 电影评分
     */
    private Float score;

    /**
     * 导演
     */
    @TableField("Director")
    private String director;

    /**
     * 编剧
     */
    @TableField("Screenwriter")
    private String screenwriter;

    /**
     * 主演
     */
    @TableField("Starring")
    private String starring;

    /**
     * 类别编号
     */
    private Integer typeId;

    /**
     * 国家/地区
     */
    @TableField("Country_Region")
    private String countryRegion;

    /**
     * 语言
     */
    @TableField("Language")
    private String language;

    /**
     * 上线时间
     */
    @TableField("ReleaseDate")
    private String releasedate;

    /**
     * 时长
     */
    private String length;

    /**
     * 简介
     */
    @TableField("Introduction")
    private String introduction;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
