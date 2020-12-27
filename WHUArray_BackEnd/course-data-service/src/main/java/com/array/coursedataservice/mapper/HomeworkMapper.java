package com.array.coursedataservice.mapper;

import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.StudentHomeworkAssociation;
import com.array.commonmodule.bean.dto.HomeworkTodo;
import com.array.commonmodule.bean.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yee
 */
@Mapper
public interface HomeworkMapper {
    int addHomework(HomeWork homeWork);

    int deleteHomework(Long homeworkId);

    int updateHomework(HomeWork homeWork);

    HomeWork findHomeworkById(Long homeworkId);

    List<HomeWork> findAllHomeWork();

    List<StudentHomeworkAssociation> findStudentHomeworkAssociationByCourseIdAndUserId(Long userId, List<Long> homeworkIds);

    int submitHomework(StudentHomeworkAssociation studentHomeworkAssociation);

    List<QuestionDTO> findQuestionByHomeworkId(Long homeworkId);

    List<HomeworkTodo> findHomeWorkTodoListByUserId(Long id);

    int addStudentToHomework(@Param("homeworkId") Long homeworkId, @Param("sids") List<Long> sids);

}
