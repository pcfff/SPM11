package com.example.teachingplatform.mapper;


import com.example.teachingplatform.dto.StudentDTO;
import com.example.teachingplatform.entity.Student;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.Student
 */
public interface StudentMapper  {

    List<StudentDTO> getPageList(Student student);

    Student getById(Integer userId);

    int removeById(Integer userId);

    int updateById(Student student);

    int save(Student student);

    int saveOrUpdate(Student student);

}




