package com.example.teachingplatform.service;

import com.example.teachingplatform.dto.NoticeDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Notice;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * (Notice)表服务接口
 *
 * @author 
 */
public interface NoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notice queryById(Integer id);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 是否成功
     */
    boolean insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 是否成功
     */
    boolean update(Notice notice);

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
    PageResult<NoticeDTO> list(Page<Notice> page, Notice notice);

}

