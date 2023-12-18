package com.example.teachingplatform.mapper;

import com.example.teachingplatform.dto.ExperimentCommentChildDTO;
import com.example.teachingplatform.dto.ExperimentCommentDTO;
import com.example.teachingplatform.entity.ExperimentComment;
import java.util.List;

/**
 * (ExperimentComment)表数据库访问层
 *
 * @author 
 */
public interface ExperimentCommentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ExperimentComment queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param experimentComment 实例对象
     * @return 对象列表
     */
    List<ExperimentCommentChildDTO> queryAll(ExperimentCommentChildDTO experimentComment);

    /**
     * 新增数据
     *
     * @param experimentComment 实例对象
     * @return 影响行数
     */
    int insert(ExperimentComment experimentComment);

    /**
     * 修改数据
     *
     * @param experimentComment 实例对象
     * @return 影响行数
     */
    int update(ExperimentComment experimentComment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<ExperimentCommentDTO> list(ExperimentComment experimentComment);

    List<ExperimentCommentChildDTO> selectByReplyId(Integer replyId);
}
