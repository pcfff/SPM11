package com.example.teachingplatform.dto;


import java.util.Date;
import java.util.List;

public class ExperimentCommentDTO {

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

    private String username;
    /**
     * 回复时间
     */
    private Date createTime;
    /**
     * 章节id
     */
    private Integer experimentId;

    private List<ExperimentCommentChildDTO> commentList;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<ExperimentCommentChildDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ExperimentCommentChildDTO> commentList) {
        this.commentList = commentList;
    }
}
