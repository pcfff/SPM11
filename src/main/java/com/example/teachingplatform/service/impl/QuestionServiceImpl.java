package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.dto.QuestionDTO;
import com.example.teachingplatform.entity.Question;
import com.example.teachingplatform.mapper.QuestionMapper;
import com.example.teachingplatform.mapper.StudentMapper;
import com.example.teachingplatform.service.QuestionService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class QuestionServiceImpl implements QuestionService{

    @Override
    public PageResult<QuestionDTO> getPage(Page<Question> page, Question question) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        QuestionMapper baseMapper = sqlSession.getMapper(QuestionMapper.class);
        List<QuestionDTO> pageList = baseMapper.getPageList(question);
        sqlSession.commit();
        sqlSession.close();
        PageResult<QuestionDTO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setData(pageList);
        return pageResult;
    }

    @Override
    public List<Question> list(Question question) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        QuestionMapper baseMapper = sqlSession.getMapper(QuestionMapper.class);
        List<Question> pageList = baseMapper.list(question);
        sqlSession.commit();
        sqlSession.close();
        return pageList;
    }

    @Override
    public boolean save(Question question) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        QuestionMapper baseMapper = sqlSession.getMapper(QuestionMapper.class);
        int rows = baseMapper.save(question);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean removeById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        QuestionMapper baseMapper = sqlSession.getMapper(QuestionMapper.class);
        int rows = baseMapper.removeById(id);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean updateById(Question question) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        QuestionMapper baseMapper = sqlSession.getMapper(QuestionMapper.class);
        int rows = baseMapper.updateById(question);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public Question getById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        QuestionMapper baseMapper = sqlSession.getMapper(QuestionMapper.class);
        Question question = baseMapper.getById(id);
        sqlSession.commit();
        sqlSession.close();
        return question;
    }
}




