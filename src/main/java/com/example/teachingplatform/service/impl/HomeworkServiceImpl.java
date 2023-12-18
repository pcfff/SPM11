package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.dto.HomeworkDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Homework;
import com.example.teachingplatform.mapper.HomeworkMapper;
import com.example.teachingplatform.service.HomeworkService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class HomeworkServiceImpl implements HomeworkService{

    @Override
    public PageResult<HomeworkDTO> getPage(Page<Homework> page, Homework homework) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        HomeworkMapper baseMapper = sqlSession.getMapper(HomeworkMapper.class);
        List<HomeworkDTO> pageList = baseMapper.getPageList(homework);
        sqlSession.commit();
        sqlSession.close();
        return new PageResult<>(pageList, page.getTotal());
    }

    @Override
    public Homework getById(Integer homeworkId) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkMapper baseMapper = sqlSession.getMapper(HomeworkMapper.class);
        Homework homework = baseMapper.getById(homeworkId);
        sqlSession.commit();
        sqlSession.close();
        return homework;
    }

    @Override
    public boolean insert(Homework homework) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkMapper baseMapper = sqlSession.getMapper(HomeworkMapper.class);
        int rows = baseMapper.insert(homework);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean removeById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkMapper baseMapper = sqlSession.getMapper(HomeworkMapper.class);
        int rows = baseMapper.removeById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean updateById(Homework homework) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkMapper baseMapper = sqlSession.getMapper(HomeworkMapper.class);
        int rows = baseMapper.updateById(homework);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }
}




