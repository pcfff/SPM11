package com.example.teachingplatform.mapper;


import com.example.teachingplatform.entity.Teacher;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.Teacher
 */
public interface TeacherMapper  {

    Teacher getById(Integer userId);

    int saveOrUpdate(Teacher teacher);

    List<Teacher> list(Teacher teacher);


}




