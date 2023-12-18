package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.dto.ExperimentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Experiment;
import com.example.teachingplatform.mapper.ExperimentMapper;
import com.example.teachingplatform.mapper.HomeworkMapper;
import com.example.teachingplatform.service.ExperimentService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class ExperimentServiceImpl implements ExperimentService{

    @Override
    public PageResult<ExperimentDTO> getPage(Page<Experiment> page, Experiment experiment) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentMapper baseMapper = sqlSession.getMapper(ExperimentMapper.class);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ExperimentDTO> pageList = baseMapper.getPageList(experiment);
        sqlSession.commit();
        sqlSession.close();
        return new PageResult<>(pageList, page.getTotal());
    }

    @Override
    public boolean save(Experiment experiment) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentMapper baseMapper = sqlSession.getMapper(ExperimentMapper.class);
        int rows = baseMapper.save(experiment);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean removeById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentMapper baseMapper = sqlSession.getMapper(ExperimentMapper.class);
        int rows = baseMapper.removeById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean updateById(Experiment experiment) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentMapper baseMapper = sqlSession.getMapper(ExperimentMapper.class);
        int rows = baseMapper.updateById(experiment);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public Experiment getById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentMapper baseMapper = sqlSession.getMapper(ExperimentMapper.class);
        Experiment experiment = baseMapper.getById(id);
        sqlSession.commit();
        sqlSession.close();
        return experiment;
    }
}




