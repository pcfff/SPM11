package com.example.teachingplatform.service.impl;

import com.example.teachingplatform.dto.NoticeDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Notice;
import com.example.teachingplatform.mapper.NoticeMapper;
import com.example.teachingplatform.service.NoticeService;
import com.example.teachingplatform.util.DataSourceUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * (Notice)表服务实现类
 *
 * @author 
 */
public class NoticeServiceImpl implements NoticeService {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Notice queryById(Integer id) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        NoticeMapper baseMapper = sqlSession.getMapper(NoticeMapper.class);
        Notice notice = baseMapper.queryById(id);
        sqlSession.commit();
        sqlSession.close();
        return notice;
    }

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Notice notice) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        NoticeMapper baseMapper = sqlSession.getMapper(NoticeMapper.class);
        int rows = baseMapper.insert(notice);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Notice notice) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        NoticeMapper baseMapper = sqlSession.getMapper(NoticeMapper.class);
        int rows = baseMapper.update(notice);
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
        NoticeMapper baseMapper = sqlSession.getMapper(NoticeMapper.class);
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
    public PageResult<NoticeDTO> list(Page<Notice> page, Notice notice){
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        NoticeMapper baseMapper = sqlSession.getMapper(NoticeMapper.class);
        List<NoticeDTO> pageList = baseMapper.queryAll(notice);
        sqlSession.commit();
        sqlSession.close();
        PageResult<NoticeDTO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setData(pageList);
        return pageResult;
    }

   
}
