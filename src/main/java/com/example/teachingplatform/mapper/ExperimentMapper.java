package com.example.teachingplatform.mapper;


import com.example.teachingplatform.dto.ExperimentDTO;
import com.example.teachingplatform.entity.Experiment;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.Experiment
 */
public interface ExperimentMapper {

    List<ExperimentDTO> getPageList(Experiment experiment);

    int save(Experiment experiment);

    int removeById(Integer id);

    int updateById(Experiment experiment);

    Experiment getById(Integer id);
}




