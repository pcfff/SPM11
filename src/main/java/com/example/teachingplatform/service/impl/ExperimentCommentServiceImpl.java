package com.example.teachingplatform.service.impl;

import com.example.teachingplatform.dto.ExperimentCommentChildDTO;
import com.example.teachingplatform.dto.ExperimentCommentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.ExperimentComment;
import com.example.teachingplatform.mapper.ExperimentCommentMapper;
import com.example.teachingplatform.service.ExperimentCommentService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * (ExperimentComment)表服务实现类
 *
 * @author 
 */
public class ExperimentCommentServiceImpl implements ExperimentCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExperimentComment queryById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentCommentMapper baseMapper = sqlSession.getMapper(ExperimentCommentMapper.class);
        ExperimentComment experimentComment = baseMapper.queryById(id);
        sqlSession.commit();
        sqlSession.close();
        return experimentComment;
    }

    /**
     * 新增数据
     *
     * @param experimentComment 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(ExperimentComment experimentComment) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentCommentMapper baseMapper = sqlSession.getMapper(ExperimentCommentMapper.class);
        int rows = baseMapper.insert(experimentComment);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    /**
     * 修改数据
     *
     * @param experimentComment 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(ExperimentComment experimentComment) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentCommentMapper baseMapper = sqlSession.getMapper(ExperimentCommentMapper.class);
        int rows = baseMapper.update(experimentComment);
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
        ExperimentCommentMapper baseMapper = sqlSession.getMapper(ExperimentCommentMapper.class);
        int rows = baseMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }
    
    /**
     * 条件查询所有数据
     *
     */
    @Override
    public List<ExperimentCommentDTO> list(ExperimentComment experimentComment){
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentCommentMapper baseMapper = sqlSession.getMapper(ExperimentCommentMapper.class);
        List<ExperimentCommentDTO> list = baseMapper.list(experimentComment);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public PageResult<ExperimentCommentChildDTO> getPageList(Page<ExperimentComment> page, ExperimentCommentChildDTO experimentComment) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ExperimentCommentMapper baseMapper = sqlSession.getMapper(ExperimentCommentMapper.class);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ExperimentCommentChildDTO> pageList = baseMapper.queryAll(experimentComment);
        sqlSession.commit();
        sqlSession.close();
        return new PageResult<>(pageList, page.getTotal());
    }


}
