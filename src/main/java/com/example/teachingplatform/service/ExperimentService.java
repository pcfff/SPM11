package com.example.teachingplatform.service;


import com.example.teachingplatform.dto.ExperimentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Experiment;
import com.github.pagehelper.Page;

/**
 *
 */
public interface ExperimentService {

    PageResult<ExperimentDTO> getPage(Page<Experiment> page, Experiment experiment);

    boolean save(Experiment experiment);

    boolean removeById(Integer id);

    boolean updateById(Experiment experiment);

    Experiment getById(Integer id);
}
