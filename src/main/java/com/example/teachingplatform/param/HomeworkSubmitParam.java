package com.example.teachingplatform.param;


import java.util.List;

public class HomeworkSubmitParam {

    private Integer homeworkId;
    private List<AnswerParam> list;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public List<AnswerParam> getList() {
        return list;
    }

    public void setList(List<AnswerParam> list) {
        this.list = list;
    }
}
