package com.example.teachingplatform.entity;



import java.io.Serializable;
import java.util.Date;


/**
 * (ExperimentComment)实体类
 *
 * @author 
 */

public class ExperimentComment implements Serializable {
    /**
    * ID
    */
    private Integer id;
    /**
    * 评论内容
    */
    private String content;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 回复评论id
    */
    private Integer replyId;
    /**
    * 回复时间
    */
    private Date createTime;
    /**
    * 章节id
    */
    private Integer experimentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }
}
