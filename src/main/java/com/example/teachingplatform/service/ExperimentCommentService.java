package com.example.teachingplatform.service;

import com.example.teachingplatform.dto.ExperimentCommentChildDTO;
import com.example.teachingplatform.dto.ExperimentCommentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.ExperimentComment;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * (ExperimentComment)表服务接口
 *
 * @author 
 */
public interface ExperimentCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExperimentComment queryById(Integer id);

    /**
     * 新增数据
     *
     * @param experimentComment 实例对象
     * @return 是否成功
     */
    boolean insert(ExperimentComment experimentComment);

    /**
     * 修改数据
     *
     * @param experimentComment 实例对象
     * @return 是否成功
     */
    boolean update(ExperimentComment experimentComment);

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
    List<ExperimentCommentDTO> list(ExperimentComment experimentComment);

    PageResult<ExperimentCommentChildDTO> getPageList(Page<ExperimentComment> page, ExperimentCommentChildDTO experimentComment);
}

