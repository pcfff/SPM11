package com.example.teachingplatform.mapper;

import com.example.teachingplatform.dto.CourseResourceDTO;
import com.example.teachingplatform.entity.CourseResource;
import java.util.List;

/**
 * (CourseResource)表数据库访问层
 *
 * @author 
 */
public interface CourseResourceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CourseResource queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param courseResource 实例对象
     * @return 对象列表
     */
    List<CourseResourceDTO> queryAll(CourseResource courseResource);

    /**
     * 新增数据
     *
     * @param courseResource 实例对象
     * @return 影响行数
     */
    int insert(CourseResource courseResource);

    /**
     * 修改数据
     *
     * @param courseResource 实例对象
     * @return 影响行数
     */
    int update(CourseResource courseResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
