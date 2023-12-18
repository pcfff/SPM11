package com.example.teachingplatform.service;

import com.example.teachingplatform.entity.User;

/**
 *
 */
public interface UserService {

    User getById(Integer userId);

    boolean update(User updateUser);

    boolean save(User user);

    User login(User user);
}
