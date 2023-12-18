package com.example.teachingplatform.service;


import com.example.teachingplatform.dto.HomeworkSubmitDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.HomeworkSubmit;
import com.github.pagehelper.Page;

import java.util.List;

/**
 *
 */
public interface HomeworkSubmitService {

    PageResult<HomeworkSubmitDTO> getPage(Page<HomeworkSubmit> page, HomeworkSubmitDTO homeworkSubmit, Integer teacherId);

    List<HomeworkSubmit> list(HomeworkSubmit homeworkSubmit);

    boolean saveBatch(List<HomeworkSubmit> list);


    boolean updateBatch(List<HomeworkSubmit> list);

}
