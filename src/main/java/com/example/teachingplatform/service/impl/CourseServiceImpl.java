package com.example.teachingplatform.service.impl;

import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Course;
import com.example.teachingplatform.mapper.CourseMapper;
import com.example.teachingplatform.service.CourseService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class CourseServiceImpl implements CourseService{

    @Override
    public List<Course> list(Course course) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseMapper baseMapper = sqlSession.getMapper(CourseMapper.class);
        List<Course> pageList = baseMapper.list(course);
        sqlSession.commit();
        sqlSession.close();
        return pageList;
    }

    @Override
    public PageResult<Course> getPageList(Page<Course> page, Course course) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        CourseMapper baseMapper = sqlSession.getMapper(CourseMapper.class);
        List<Course> pageList = baseMapper.list(course);
        sqlSession.commit();
        sqlSession.close();
        return new PageResult<>(pageList, page.getTotal());
    }

    @Override
    public Course getById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseMapper baseMapper = sqlSession.getMapper(CourseMapper.class);
        Course course = baseMapper.getById(id);
        sqlSession.commit();
        sqlSession.close();
        return course;
    }

    @Override
    public boolean save(Course course) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseMapper baseMapper = sqlSession.getMapper(CourseMapper.class);
        int rows = baseMapper.save(course);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean removeById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseMapper baseMapper = sqlSession.getMapper(CourseMapper.class);
        int rows = baseMapper.removeById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean updateById(Course course) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseMapper baseMapper = sqlSession.getMapper(CourseMapper.class);
        int rows = baseMapper.updateById(course);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }
}




