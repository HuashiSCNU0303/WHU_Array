package com.array.arrayserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.array.arrayserver.Utils.UserUtils;
import com.array.arrayserver.client.*;
import com.array.commonmodule.bean.*;
import com.array.commonmodule.bean.dto.CourseDTO;
import com.array.commonmodule.bean.dto.HomeworkTodo;
import com.array.commonmodule.bean.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/homework")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class HomeworkController {
    @Autowired
    HomeworkClientFeign homeworkClientFeign;

    @Autowired
    MessageClientFeign messageClientFeign;

    @Autowired
    CourseClientFeign courseClientFeign;

    @Autowired
    TeacherClientFeign teacherClientFeign;

    @Autowired
    QuestionClientFeign questionClientFeign;

    @PostMapping("/addHomework")
    public Long addHomework(@RequestBody HomeWork homeWork) throws IOException {
        Long homeworkId = homeworkClientFeign.addHomework(homeWork);
        CourseDTO course = courseClientFeign.findCourseById(homeWork.getCourseId());
        if (homeWork.getIsExam() == 1) {
            // 发消息
            // 把这个作业下面的题目的status也改成published
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseId", course.getCourseId());
            jsonObject.put("courseName", course.getCourseName());
            jsonObject.put("homeworkId", homeworkId);
            jsonObject.put("homeworkName", homeWork.getHomeworkName());
            jsonObject.put("isExam", homeWork.getIsExam());
            jsonObject.put("type", "Create");
            String messageContent = jsonObject.toJSONString();
            Message message = new Message((long)-1, messageContent);
            messageClientFeign.addMessage(message, homeWork.getCourseId());
            List<Student> students = courseClientFeign.findStudentByCourseId(homeWork.getCourseId());
            List<Long> sids = new LinkedList<>();
            for(Student student: students) {
                WebSocketServer.sendInfo("新消息", student.getUserId().toString());
                sids.add(student.getUserId());
            }
            homeworkClientFeign.addStudentToHomework(homeworkId, sids);
        }
        return homeworkId;
    }

    @DeleteMapping("/{homeworkId}")
    public int deleteHomework(@PathVariable("homeworkId") Long homeworkId) {
        return homeworkClientFeign.deleteHomework(homeworkId);
    }

    @PutMapping("/updateHomework")
    public int updateHomework(@RequestBody HomeWork homeWork) throws IOException {
        Long homeworkId = homeWork.getHomeworkId();
        CourseDTO course = courseClientFeign.findCourseById(homeWork.getCourseId());
        System.out.println(homeWork.getStatus());
        if ("published".equals(homeWork.getStatus())) {
            // 发消息
            // 把这个作业下面的题目的status也改成published
            questionClientFeign.updateQuestionStatus(homeworkId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseId", course.getCourseId());
            jsonObject.put("courseName", course.getCourseName());
            jsonObject.put("homeworkId", homeworkId);
            jsonObject.put("homeworkName", homeWork.getHomeworkName());
            jsonObject.put("isExam", homeWork.getIsExam());
            jsonObject.put("type", "Publish");
            String messageContent = jsonObject.toJSONString();
            Message message = new Message((long)-1, messageContent);
            messageClientFeign.addMessage(message, homeWork.getCourseId());
            List<Student> students = courseClientFeign.findStudentByCourseId(homeWork.getCourseId());
            List<Long> sids = new LinkedList<>();
            for(Student student: students) {
                WebSocketServer.sendInfo("新消息", student.getUserId().toString());
                sids.add(student.getUserId());
            }
            homeworkClientFeign.addStudentToHomework(homeworkId, sids);
        }
        return homeworkClientFeign.updateHomework(homeWork);
    }

    @GetMapping("/{homeworkId}")
    public HomeWork findHomeworkById(@PathVariable("homeworkId") Long homeworkId) {
        return homeworkClientFeign.findHomeworkById(homeworkId);
    }

    @GetMapping("/all")
    public List<HomeWork> findAllHomeWork() {
        return homeworkClientFeign.findAllHomeWork();
    }

    @PostMapping("/submitHomework/{homeworkId}")
    public int submitHomework(@PathVariable("homeworkId") Long homeworkId) {
        Long studentId = UserUtils.getCurrentUser().getUserId();
        return homeworkClientFeign.submitHomework(studentId, homeworkId);
    }

    @GetMapping("/findStudentHomeworkAssociationByCourseIdAndUserId")
    public List<StudentHomeworkAssociation> findStudentHomeworkAssociationByCourseIdAndUserId(@RequestParam Long userId, @RequestParam Long courseId) {
        return homeworkClientFeign.findStudentHomeworkAssociationByCourseIdAndUserId(userId, courseId);
    }

    @GetMapping("/{homeworkId}/allQuestion")
    public List<QuestionDTO> findQuestionByHomeWorkId(@PathVariable("homeworkId") Long homeworkId) {
        return homeworkClientFeign.findQuestionByHomeWorkId(homeworkId);
    }

    @GetMapping("/homeworkTodoList/{userId}")
    public List<HomeworkTodo> findHomeWorkTodoListByUserId(@PathVariable("userId") Long id) {
        return homeworkClientFeign.findHomeWorkTodoListByUserId(id);
    }
}
