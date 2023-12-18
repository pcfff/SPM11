package com.example.teachingplatform.service;


import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Course;
import com.github.pagehelper.Page;

import java.util.List;

/**
 *
 */
public interface CourseService  {

    List<Course> list(Course course);

    PageResult<Course> getPageList(Page<Course> page, Course course);

    Course getById(Integer id);

    boolean save(Course course);

    boolean removeById(Integer id);

    boolean updateById(Course course);
}
