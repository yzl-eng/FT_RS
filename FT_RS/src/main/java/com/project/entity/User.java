package com.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String headshot;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    @TableField("userpassword")
    private String password;

    /**
     * 账号身份
     */
    @TableField("userclass")
    private String role;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT) //创建时自动填充
    private LocalDateTime createTime;


}
