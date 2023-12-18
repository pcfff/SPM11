package com.example.teachingplatform.mapper;

import com.example.teachingplatform.dto.HomeworkSubmitDTO;
import com.example.teachingplatform.entity.HomeworkSubmit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.teachingplatform.entity.HomeworkSubmit
 */
public interface HomeworkSubmitMapper  {

    List<HomeworkSubmitDTO> getPageList(@Param("entity") HomeworkSubmitDTO homeworkSubmit, @Param("teacherId") Integer teacherId);

    List<HomeworkSubmit> list(HomeworkSubmit homeworkSubmit);

    int saveBatch(List<HomeworkSubmit> list);

    int updateById(HomeworkSubmit homeworkSubmit);

}




