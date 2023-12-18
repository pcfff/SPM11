package com.example.teachingplatform.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName homework
 */

public class Homework implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 教师id
     */
    private Integer userId;

    /**
     * 作业名称
     */
    private String name;

    /**
     * 截止时间
     */
    private Date deadline;

    /**
     * 问题id
     */
    private String questionIds;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 类型 0 作业 1 考试
     */
    private Integer type;

    /**
     * 批改类型 0 自动批改 1 手动批改
     */
    private Integer correct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }
}