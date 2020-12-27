package com.array.coursedataservice.service;

import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.UseCase;
import com.array.commonmodule.bean.dto.QuestionDTO;
import com.array.coursedataservice.mapper.QuestionMapper;
import com.array.coursedataservice.mapper.UseCaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yee
 */
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UseCaseMapper useCaseMapper;

    public int addQuestion(Question question) {
        return questionMapper.addQuestion(question);
    }

    public int deleteQuestion(Long questionId) {
        return questionMapper.deleteQuestion(questionId);
    }

    public int updateQuestion(Question question) {
        useCaseMapper.deleteUseCaseByQuestionId(question.getQuestionId());
        useCaseMapper.addUseCases(question.getUseCases());
        return questionMapper.updateQuestion(question);
    }

    public Question findQuestionById(Long questionId) {
        return questionMapper.findQuestionById(questionId);
    }

    public List<Question> findQuestionByName(String questionName) {
        return questionMapper.findQuestionByName(questionName);
    }

    public int updateQuestionStatus(Long homeworkId) {
        return questionMapper.updateQuestionStatus(homeworkId);
    }

    public List<QuestionDTO> findAllQuestion() {
        return questionMapper.findAllQuestion();
    }

    public List<Question> findQuestionByHomeworkId(Long id) {
        return questionMapper.findQuestionByHomeworkId(id);
    }
}
