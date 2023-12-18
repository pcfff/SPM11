package com.example.teachingplatform.mapper;

import com.example.teachingplatform.entity.User;

/**
 * @Entity com.example.teachingplatform.entity.User
 */
public interface UserMapper {

    User getById(Integer userId);

    int update(User user);

    int save(User user);

    User login(User user);

}




