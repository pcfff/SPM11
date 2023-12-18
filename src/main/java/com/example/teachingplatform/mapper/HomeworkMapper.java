package com.example.teachingplatform.mapper;


import com.example.teachingplatform.dto.HomeworkDTO;
import com.example.teachingplatform.entity.Homework;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.Homework
 */
public interface HomeworkMapper  {

    List<HomeworkDTO> getPageList(Homework homework);

    Homework getById(Integer homeworkId);

    int insert(Homework homework);

    int removeById(Integer id);

    int updateById(Homework homework);
}




