package com.array.arrayserver.client;

import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.dto.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yee
 */
@FeignClient(value = "COURSE-DATA-SERVICE")
@Service
public interface QuestionClientFeign {
    @PostMapping("/Question/addQuestion")
    public Long addQuestion(@RequestBody Question question);

    @DeleteMapping("/Question/{questionId}")
    public int deleteQuestion(@PathVariable("questionId") Long questionId);

    @PutMapping("/Question/updateQuestion")
    public int updateQuestion(@RequestBody Question question);

    @PutMapping("/Question/updateQuestionStatus")
    public int updateQuestionStatus(@RequestParam Long homeworkId);

    @GetMapping("/Question/{questionId}")
    public Question findQuestionById(@PathVariable("questionId") Long questionId);

    @GetMapping("/Question/findQuestionByName")
    public List<Question> findQuestionByName(@RequestParam String questionName);

    @GetMapping("/Question/all")
    public List<QuestionDTO> findAllQuestion();

    @GetMapping("/Question/findQuestionByHomeworkId/{homeworkId}")
    public List<Question> findQuestionByHomeworkId(@PathVariable("homeworkId") Long id);
}
