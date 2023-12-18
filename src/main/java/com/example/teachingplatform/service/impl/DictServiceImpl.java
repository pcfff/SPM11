package com.example.teachingplatform.service.impl;

import com.example.teachingplatform.entity.Dict;
import com.example.teachingplatform.mapper.DictMapper;
import com.example.teachingplatform.service.DictService;
import com.example.teachingplatform.util.DataSourceUtil;
import org.apache.ibatis.session.SqlSession;



/**
 * (Dict)表服务实现类
 *
 * @author 
 */
public class DictServiceImpl implements DictService {

    /**
     * 通过ID查询单条数据
     *
     * @param dictKey 主键
     * @return 实例对象
     */
    @Override
    public Dict queryById(String dictKey) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        DictMapper baseMapper = sqlSession.getMapper(DictMapper.class);
        Dict dict = baseMapper.queryById(dictKey);
        sqlSession.commit();
        sqlSession.close();
        return dict;
    }


    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Dict dict) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        DictMapper baseMapper = sqlSession.getMapper(DictMapper.class);
        int update = baseMapper.update(dict);
        sqlSession.commit();
        sqlSession.close();
        return update == 1;
    }


   
}
