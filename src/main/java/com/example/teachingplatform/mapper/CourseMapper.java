package com.example.teachingplatform.mapper;


import com.example.teachingplatform.entity.Course;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.Course
 */
public interface CourseMapper  {

    int removeById(Integer id);

    int updateById(Course course);

    int save(Course course);

    Course getById(Integer id);

    List<Course> list(Course course);

}




