package com.array.coursedataservice.controller;

import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.Record;
import com.array.commonmodule.bean.StudentHomeworkAssociation;
import com.array.commonmodule.bean.dto.HomeworkTodo;
import com.array.commonmodule.bean.dto.QuestionDTO;
import com.array.coursedataservice.mapper.HomeworkMapper;
import com.array.coursedataservice.service.HomeworkService;
import com.array.coursedataservice.service.QuestionService;
import com.array.coursedataservice.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/Homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @Autowired
    RecordService recordService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/addHomework")
    public Long addHomework(@RequestBody HomeWork homeWork) {
        homeworkService.addHomework(homeWork);
        return homeWork.getHomeworkId();

        // sendMessage();
    }

    @DeleteMapping("/{homeworkId}")
    public int deleteHomework(@PathVariable("homeworkId") Long homeworkId) {
        return homeworkService.deleteHomework(homeworkId);
    }

    @PutMapping("/updateHomework")
    public int updateHomework(@RequestBody HomeWork homeWork) {
        return homeworkService.updateHomework(homeWork);
    }

    @GetMapping("/{homeworkId}")
    public HomeWork findHomeworkById(@PathVariable("homeworkId") Long homeworkId) {
        return homeworkService.findHomeworkById(homeworkId);
    }

    @GetMapping("/findStudentHomeworkAssociationByCourseIdAndUserId")
    public List<StudentHomeworkAssociation> findStudentHomeworkAssociationByCourseIdAndUserId(@RequestParam Long userId, @RequestParam Long courseId) {
        return homeworkService.findStudentHomeworkAssociationByCourseIdAndUserId(userId, courseId);
    }

    @GetMapping("/all")
    public List<HomeWork> findAllHomeWork() {
        return homeworkService.findAllHomeWork();
    }

    @PostMapping("/submitHomework")
    public int submitHomework(@RequestParam Long studentId, @RequestParam Long homeworkId) {
        StudentHomeworkAssociation studentHomeworkAssociation = new StudentHomeworkAssociation();
        studentHomeworkAssociation.setUserId(studentId);
        studentHomeworkAssociation.setHomeworkId(homeworkId);
        List<Question> questions = questionService.findQuestionByHomeworkId(homeworkId);
        int grade = 0;
        for(int i = 0; i < questions.size(); i++) {
            List<Record> records = recordService.findRecordByQuestionIdAndUserId(questions.get(i).getQuestionId(), studentId);
            int max = 0;
            for(int j = 0; j < records.size(); j++) {
                if(records.get(j).getRecordGrade() > max) {
                    max = (records.get(j).getRecordGrade()).intValue();
                }
            }
            grade += max;
            // 求最大值
        }
        int averageGrade = grade / questions.size();
        studentHomeworkAssociation.setGrade(averageGrade);
        homeworkService.submitHomework(studentHomeworkAssociation);
        return averageGrade;
    }

    @GetMapping("/{homeworkId}/allQuestion")
    public List<QuestionDTO> findQuestionByHomeWorkId(@PathVariable("homeworkId") Long homeworkId) {
        return homeworkService.findQuestionByHomeworkId(homeworkId);
    }

    @GetMapping("/homeworkTodoList/{userId}")
    public List<HomeworkTodo> findHomeWorkTodoListByUserId(@PathVariable("userId") Long id) {
        return homeworkService.findHomeWorkTodoListByUserId(id);
    }

    @PostMapping("/addStudentToHomework")
    public int addStudentToHomework(@RequestParam Long homeworkId, @RequestParam List<Long> sids) {
        return homeworkService.addStudentToHomework(homeworkId, sids);
    }
}
