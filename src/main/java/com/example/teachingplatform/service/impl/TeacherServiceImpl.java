package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.entity.Teacher;
import com.example.teachingplatform.mapper.TeacherMapper;
import com.example.teachingplatform.service.TeacherService;
import com.example.teachingplatform.util.DataSourceUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class TeacherServiceImpl
        implements TeacherService {

    @Override
    public boolean saveOrUpdate(Teacher teacher) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        TeacherMapper baseMapper = sqlSession.getMapper(TeacherMapper.class);
        int rows = baseMapper.saveOrUpdate(teacher);
        sqlSession.commit();
        sqlSession.close();
        return rows > 0;
    }

    @Override
    public Teacher getById(Integer userId) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        TeacherMapper baseMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = baseMapper.getById(userId);
        sqlSession.commit();
        sqlSession.close();
        return teacher;
    }

    @Override
    public List<Teacher> list() {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        TeacherMapper baseMapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> list = baseMapper.list(null);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}




