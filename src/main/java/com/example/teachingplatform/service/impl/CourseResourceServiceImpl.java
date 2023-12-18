package com.example.teachingplatform.service.impl;

import com.example.teachingplatform.dto.CourseResourceDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.CourseResource;
import com.example.teachingplatform.mapper.CourseResourceMapper;
import com.example.teachingplatform.service.CourseResourceService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * (CourseResource)表服务实现类
 *
 * @author 
 */
public class CourseResourceServiceImpl implements CourseResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CourseResource queryById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseResourceMapper baseMapper = sqlSession.getMapper(CourseResourceMapper.class);
        CourseResource courseResource = baseMapper.queryById(id);
        sqlSession.commit();
        sqlSession.close();
        return courseResource;
    }

    /**
     * 新增数据
     *
     * @param courseResource 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(CourseResource courseResource) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseResourceMapper baseMapper = sqlSession.getMapper(CourseResourceMapper.class);
        int rows = baseMapper.insert(courseResource);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    /**
     * 修改数据
     *
     * @param courseResource 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(CourseResource courseResource) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseResourceMapper baseMapper = sqlSession.getMapper(CourseResourceMapper.class);
        int rows = baseMapper.update(courseResource);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        CourseResourceMapper baseMapper = sqlSession.getMapper(CourseResourceMapper.class);
        int rows = baseMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }
    
    /**
     * 通过条件查询数据
     *
     * @return 结果集
     */
    @Override
    public PageResult<CourseResourceDTO> list(Page<CourseResource> page, CourseResource courseResource) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        CourseResourceMapper baseMapper = sqlSession.getMapper(CourseResourceMapper.class);
        List<CourseResourceDTO> pageList = baseMapper.queryAll(courseResource);
        sqlSession.commit();
        sqlSession.close();
        return new PageResult<>(pageList, page.getTotal());
    }
    
}
