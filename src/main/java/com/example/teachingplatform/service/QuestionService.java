package com.example.teachingplatform.service;


import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.dto.QuestionDTO;
import com.example.teachingplatform.entity.Question;
import com.github.pagehelper.Page;

import java.util.List;

/**
 *
 */
public interface QuestionService{

    PageResult<QuestionDTO> getPage(Page<Question> page, Question question);

    List<Question> list(Question question);

    boolean save(Question question);

    boolean removeById(Integer id);

    boolean updateById(Question question);

    Question getById(Integer id);
}
