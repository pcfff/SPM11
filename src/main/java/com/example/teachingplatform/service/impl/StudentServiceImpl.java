package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.dto.StudentDTO;
import com.example.teachingplatform.entity.Student;
import com.example.teachingplatform.mapper.StudentMapper;
import com.example.teachingplatform.service.StudentService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class StudentServiceImpl implements StudentService{


    @Override
    public PageResult<StudentDTO> getPage(Page<Student> page, Student student) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        StudentMapper baseMapper = sqlSession.getMapper(StudentMapper.class);
        List<StudentDTO> pageList = baseMapper.getPageList(student);
        sqlSession.commit();
        sqlSession.close();
        PageResult<StudentDTO> pageDTO = new PageResult<>();
        pageDTO.setTotal(page.getTotal());
        pageDTO.setData(pageList);
        return pageDTO;
    }

    @Override
    public Student getById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        StudentMapper baseMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = baseMapper.getById(id);
        sqlSession.commit();
        sqlSession.close();
        return student;
    }

    @Override
    public boolean removeById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        StudentMapper baseMapper = sqlSession.getMapper(StudentMapper.class);
        int rows = baseMapper.removeById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean updateById(Student student) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        StudentMapper baseMapper = sqlSession.getMapper(StudentMapper.class);
        int rows = baseMapper.updateById(student);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean save(Student student) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        StudentMapper baseMapper = sqlSession.getMapper(StudentMapper.class);
        int rows = baseMapper.save(student);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean saveOrUpdate(Student student) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        StudentMapper baseMapper = sqlSession.getMapper(StudentMapper.class);
        int rows = baseMapper.saveOrUpdate(student);
        sqlSession.commit();
        sqlSession.close();
        return rows > 0;
    }
}




