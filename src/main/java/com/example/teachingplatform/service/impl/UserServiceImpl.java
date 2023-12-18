package com.example.teachingplatform.service.impl;


import com.example.teachingplatform.entity.User;
import com.example.teachingplatform.mapper.UserMapper;
import com.example.teachingplatform.service.UserService;
import com.example.teachingplatform.util.DataSourceUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 */
public class UserServiceImpl implements UserService{

    @Override
    public User getById(Integer userId) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        UserMapper baseMapper = sqlSession.getMapper(UserMapper.class);
        User user = baseMapper.getById(userId);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    @Override
    public boolean update(User updateUser) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        UserMapper baseMapper = sqlSession.getMapper(UserMapper.class);
        int rows = baseMapper.update(updateUser);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public boolean save(User user) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        UserMapper baseMapper = sqlSession.getMapper(UserMapper.class);
        int rows = baseMapper.save(user);
        sqlSession.commit();
        sqlSession.close();
        return rows == 1;
    }

    @Override
    public User login(User user) {
        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        UserMapper baseMapper = sqlSession.getMapper(UserMapper.class);
        User loginUser = baseMapper.login(user);
        sqlSession.commit();
        sqlSession.close();
        return loginUser;
    }
}




