package com.example.teachingplatform.entity;


import java.io.Serializable;

/**
 * 
 * @TableName question
 */

public class Question implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 题目
     */
    private String name;

    /**
     * 类型：选择题、判断题、简答题
     */
    private Integer type;

    /**
     * 附加内容
     */
    private String options;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 出题人id
     */
    private Integer userId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 解析
     */
    private String detail;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}