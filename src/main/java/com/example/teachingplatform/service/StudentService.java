package com.example.teachingplatform.service;


import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.dto.StudentDTO;
import com.example.teachingplatform.entity.Student;
import com.github.pagehelper.Page;

/**
 *
 */
public interface StudentService  {

    PageResult<StudentDTO> getPage(Page<Student> page, Student student);

    Student getById(Integer id);

    boolean removeById(Integer id);

    boolean updateById(Student student);

    boolean save(Student student);

    boolean saveOrUpdate(Student student);

}
