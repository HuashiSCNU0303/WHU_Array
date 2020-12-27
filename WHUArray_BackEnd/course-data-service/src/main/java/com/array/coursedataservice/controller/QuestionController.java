package com.array.coursedataservice.controller;

import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.dto.QuestionDTO;
import com.array.coursedataservice.service.QuestionService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/addQuestion")
    public Long addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return question.getQuestionId();
    }

    @DeleteMapping("/{questionId}")
    public int deleteQuestion(@PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestion(questionId);
    }

    @PutMapping("/updateQuestion")
    public int updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @PutMapping("/updateQuestionStatus")
    public int updateQuestionStatus(@RequestParam Long homeworkId) {
        return questionService.updateQuestionStatus(homeworkId);
    }

    @GetMapping("/{questionId}")
    public Question findQuestionById(@PathVariable("questionId") Long questionId) {
        return questionService.findQuestionById(questionId);
    }

    @GetMapping("/findQuestionByName")
    public List<Question> findQuestionByName(@RequestParam String questionName) {
        return questionService.findQuestionByName(questionName);
    }

    @GetMapping("/all")
    public List<QuestionDTO> findAllQuestion() {
        return questionService.findAllQuestion();
    }

    @GetMapping("/findQuestionByHomeworkId/{homeworkId}")
    public List<Question> findQuestionByHomeworkId(@PathVariable("homeworkId") Long id) {
        return questionService.findQuestionByHomeworkId(id);
    }


}
