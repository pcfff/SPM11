package com.example.teachingplatform.entity;



import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName course
 */

public class Course implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课时
     */
    private Integer period;

    /**
     * 学分
     */
    private Integer score;

    /**
     * 授课老师
     */
    private Integer userId;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 专业
     */
    private String major;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}