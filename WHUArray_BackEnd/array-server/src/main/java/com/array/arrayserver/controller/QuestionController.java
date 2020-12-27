package com.array.arrayserver.controller;

import com.array.arrayserver.Utils.UserUtils;
import com.array.arrayserver.client.QuestionClientFeign;
import com.array.arrayserver.client.RecordClientFeign;
import com.array.arrayserver.client.UseCaseClientFeign;
import com.array.arrayserver.service.CompileService;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.UseCase;
import com.array.commonmodule.bean.dto.QuestionDTO;
import com.array.commonmodule.bean.dto.TestCase;
import com.array.commonmodule.bean.dto.TestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/question")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class QuestionController {
    @Autowired
    QuestionClientFeign questionClientFeign;
    @Autowired
    RecordClientFeign recordClientFeign;
    @Autowired
    CompileService compileService;
    @Autowired
    UseCaseClientFeign useCaseClientFeign;

    @PostMapping("/addQuestion")
    public Long addQuestion(@RequestBody Question question) {
        return questionClientFeign.addQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public int deleteQuestion(@PathVariable("questionId") Long questionId) {
        return questionClientFeign.deleteQuestion(questionId);
    }

    @PutMapping("/updateQuestion")
    public int updateQuestion(@RequestBody Question question) {
        questionClientFeign.updateQuestion(question);
        List<UseCase> useCaseList = useCaseClientFeign.getUseCaseByQuestionId(question.getQuestionId());
        TestList testList = new TestList();
        List<TestCase> testCaseList = new ArrayList<>();
        testList.setTestCaseList(testCaseList);
        for(UseCase u: useCaseList) {
            TestCase testCase = new TestCase(u.getUseCaseId(), u.getQuestionId(), u.getInput());
            testList.getTestCaseList().add(testCase);
        }
        testList.setProblemId(question.getQuestionId());
        compileService.addTestCases(testList);
        return 1;
    }

    @GetMapping("/{questionId}")
    public QuestionDTO findQuestionById(@PathVariable("questionId") Long questionId) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion(questionClientFeign.findQuestionById(questionId));
        questionDTO.setRecords(recordClientFeign.findRecordByQuestionIdAndUserId(questionId, UserUtils.getCurrentUser().getUserId()));
        return questionDTO;
    }

    @GetMapping("/findQuestionByName")
    public List<Question> findQuestionByName(@RequestParam String questionName) {
        return questionClientFeign.findQuestionByName(questionName);
    }

    @GetMapping("/all")
    public List<QuestionDTO> findAllQuestion() {
        return questionClientFeign.findAllQuestion();
    }

    @GetMapping("/findQuestionByHomeworkId/{homeworkId}")
    public List<Question> findQuestionByHomeworkId(@PathVariable("homeworkId") Long id) {
        return questionClientFeign.findQuestionByHomeworkId(id);
    }

}
