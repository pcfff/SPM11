package com.example.teachingplatform.service;

import com.example.teachingplatform.entity.Dict;
import java.util.List;

/**
 * (Dict)表服务接口
 *
 * @author 
 */
public interface DictService {

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
     * @return 是否成功
     */
    boolean update(Dict dict);


}

