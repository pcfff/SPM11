package com.example.teachingplatform.service;


import com.example.teachingplatform.dto.HomeworkDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Homework;
import com.github.pagehelper.Page;

/**
 *
 */
public interface HomeworkService {

    PageResult<HomeworkDTO> getPage(Page<Homework> page, Homework homework);

    Homework getById(Integer homeworkId);

    boolean insert(Homework homework);

    boolean removeById(Integer id);

    boolean updateById(Homework homework);
}
