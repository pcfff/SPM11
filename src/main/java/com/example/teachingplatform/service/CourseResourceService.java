package com.example.teachingplatform.service;

import com.example.teachingplatform.dto.CourseResourceDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.CourseResource;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * (CourseResource)表服务接口
 *
 * @author 
 */
public interface CourseResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CourseResource queryById(Integer id);

    /**
     * 新增数据
     *
     * @param courseResource 实例对象
     * @return 是否成功
     */
    boolean insert(CourseResource courseResource);

    /**
     * 修改数据
     *
     * @param courseResource 实例对象
     * @return 是否成功
     */
    boolean update(CourseResource courseResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    
    /**
     * 条件查询所有数据
     *
     */
    PageResult<CourseResourceDTO> list(Page<CourseResource> page, CourseResource courseResource);

}

