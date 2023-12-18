package com.example.teachingplatform.dto;



import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName experiment
 */
public class ExperimentDTO implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 教学资料名称
     */
    private String name;

    /**
     * 老师id
     */
    private Integer userId;

    /**
     * 课程id
     */
    private Integer courseId;

    private String courseName;

    /**
     * 附件
     */
    private String fileUrl;

    /**
     * 教学资料内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}