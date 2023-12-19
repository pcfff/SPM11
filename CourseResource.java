package com.example.teachingplatform.entity;



import java.io.Serializable;
import java.util.Date;


/**
 * (CourseResource)实体类
 *
 * @author 
 */

public class CourseResource implements Serializable {
    /**
    * ID
    */
    private Integer id;
    /**
    * 资源名称
    */
    private String name;
    /**
    * 资源描述
    */
    private String details;
    /**
    * 资源链接
    */
    private String filename;
    /**
    * 课程id
    */
    private Integer courseId;
    /**
    * 老师id
    */
    private Integer userId;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
