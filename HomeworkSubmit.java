package com.example.teachingplatform.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName homework_submit
 */

public class HomeworkSubmit implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 学生id
     */
    private Integer userId;

    /**
     * 作业id
     */
    private Integer homeworkId;

    /**
     * 题目id
     */
    private Integer questionId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 提交的答案
     */
    private String answer;

    /**
     * 提交时间
     */
    private Date createTime;

    /**
     * 状态 0 等待批改 1 批改已完成
     */
    private Integer status;

    /**
     * 是否正确0 错误 1正确
     */
    private Integer correct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }
}