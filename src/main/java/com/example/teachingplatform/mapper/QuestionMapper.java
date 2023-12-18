package com.example.teachingplatform.mapper;

import com.example.teachingplatform.dto.QuestionDTO;
import com.example.teachingplatform.entity.Question;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.Question
 */
public interface QuestionMapper {

    List<QuestionDTO> getPageList(Question question);

    List<Question> list(Question question);

    int save(Question question);

    int removeById(Integer id);

    int updateById(Question question);

    Question getById(Integer id);
}




