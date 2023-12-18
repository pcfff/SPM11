package com.example.teachingplatform.dto;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName homework_submit
 */
public class HomeworkSubmitDTO implements Serializable {

    private Integer homeworkId;
    /**
     * 学生id
     */
    private Integer userId;

    private String studentName;

    private Integer score;
    /**
     * 提交时间
     */
    private Date createTime;

    private Integer status;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
}