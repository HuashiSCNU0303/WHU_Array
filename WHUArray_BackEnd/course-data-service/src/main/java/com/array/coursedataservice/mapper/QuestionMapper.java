package com.array.coursedataservice.mapper;

import com.array.commonmodule.bean.Course;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yee
 */
@Mapper
public interface QuestionMapper {

    int addQuestion(Question question);

    int deleteQuestion(Long questionId);

    int updateQuestion(Question question);

    int updateQuestionStatus(Long homeworkId);

    Question findQuestionById(Long questionId);

    List<Question> findQuestionByName(String questionName);

    List<QuestionDTO> findAllQuestion();

    List<Question> findQuestionByHomeworkId(Long id);
}
