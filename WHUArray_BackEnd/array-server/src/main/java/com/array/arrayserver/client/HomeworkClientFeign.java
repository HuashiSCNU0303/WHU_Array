package com.array.arrayserver.client;

import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.Record;
import com.array.commonmodule.bean.StudentHomeworkAssociation;
import com.array.commonmodule.bean.dto.HomeworkTodo;
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
public interface HomeworkClientFeign {
    @PostMapping("/Homework/addHomework")
    public Long addHomework(@RequestBody HomeWork homeWork);

    @DeleteMapping("/Homework/{homeworkId}")
    public int deleteHomework(@PathVariable("homeworkId") Long homeworkId);

    @PutMapping("/Homework/updateHomework")
    public int updateHomework(@RequestBody HomeWork homeWork);

    @GetMapping("/Homework/{homeworkId}")
    public HomeWork findHomeworkById(@PathVariable("homeworkId") Long homeworkId);

    @GetMapping("/Homework/all")
    public List<HomeWork> findAllHomeWork();

    @GetMapping("/Homework/findStudentHomeworkAssociationByCourseIdAndUserId")
    public List<StudentHomeworkAssociation> findStudentHomeworkAssociationByCourseIdAndUserId(@RequestParam Long userId, @RequestParam Long courseId);

    @PostMapping("/Homework/submitHomework")
    public int submitHomework(@RequestParam Long studentId, @RequestParam Long homeworkId);

    @GetMapping("/Homework/{homeworkId}/allQuestion")
    public List<QuestionDTO> findQuestionByHomeWorkId(@PathVariable("homeworkId") Long homeworkId);

    @GetMapping("/Homework/homeworkTodoList/{userId}")
    public List<HomeworkTodo> findHomeWorkTodoListByUserId(@PathVariable("userId") Long id);

    @PostMapping("/Homework/addStudentToHomework")
    public int addStudentToHomework(@RequestParam Long homeworkId, @RequestParam List<Long> sids);
}
