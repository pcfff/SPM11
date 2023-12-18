package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.dto.HomeworkSubmitDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.HomeworkSubmit;
import com.example.teachingplatform.mapper.HomeworkSubmitMapper;
import com.example.teachingplatform.mapper.QuestionMapper;
import com.example.teachingplatform.service.HomeworkSubmitService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class HomeworkSubmitServiceImpl implements HomeworkSubmitService {

    @Override
    public PageResult<HomeworkSubmitDTO> getPage(Page<HomeworkSubmit> page, HomeworkSubmitDTO homeworkSubmit, Integer teacherId) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        HomeworkSubmitMapper baseMapper = sqlSession.getMapper(HomeworkSubmitMapper.class);
        List<HomeworkSubmitDTO> pageList = baseMapper.getPageList(homeworkSubmit, teacherId);
        sqlSession.commit();
        sqlSession.close();
        return new PageResult<>(pageList, page.getTotal());
    }

    @Override
    public List<HomeworkSubmit> list(HomeworkSubmit homeworkSubmit) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkSubmitMapper baseMapper = sqlSession.getMapper(HomeworkSubmitMapper.class);
        List<HomeworkSubmit> list = baseMapper.list(homeworkSubmit);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public boolean saveBatch(List<HomeworkSubmit> list) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkSubmitMapper baseMapper = sqlSession.getMapper(HomeworkSubmitMapper.class);
        int rows = baseMapper.saveBatch(list);
        sqlSession.commit();
        sqlSession.close();
        return rows == list.size();
    }

    @Override
    public boolean updateBatch(List<HomeworkSubmit> list) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        HomeworkSubmitMapper baseMapper = sqlSession.getMapper(HomeworkSubmitMapper.class);
        int rows = 0;
        if (list != null && !list.isEmpty()) {
            for (HomeworkSubmit homeworkSubmit : list) {
                homeworkSubmit.setStatus(1);
                rows += baseMapper.updateById(homeworkSubmit);
            }
        }
        sqlSession.commit();
        sqlSession.close();
        return rows == list.size();
    }
}




