package com.example.teachingplatform.service;


import com.example.teachingplatform.entity.Teacher;

import java.util.List;

/**
 *
 */
public interface TeacherService {

    boolean saveOrUpdate(Teacher teacher);

    Teacher getById(Integer userId);

    List<Teacher> list();


}
