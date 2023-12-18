package com.example.teachingplatform.mapper;

import com.example.teachingplatform.entity.Dict;
import java.util.List;

/**
 * (Dict)表数据库访问层
 *
 * @author 
 */
public interface DictMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param dictKey 主键
     * @return 实例对象
     */
    Dict queryById(String dictKey);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int update(Dict dict);


}
