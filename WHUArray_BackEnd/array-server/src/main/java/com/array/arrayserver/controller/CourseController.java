package com.array.arrayserver.controller;

import com.array.arrayserver.Utils.UserUtils;
import com.array.arrayserver.client.CourseClientFeign;
import com.array.commonmodule.bean.*;
import com.array.commonmodule.bean.dto.CourseDTO;
import com.array.commonmodule.bean.dto.HomeworkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/course")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class CourseController {
    @Autowired
    CourseClientFeign courseClientFeign;


    @PostMapping("/addCourse")
    public Long addCourse(@RequestBody Course course) {
        return courseClientFeign.addCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public int deleteCourse(@PathVariable("courseId") Long courseId) {
        return courseClientFeign.deleteCourse(courseId);
    }

    @PutMapping("/updateCourse")
    public int updateCourse(@RequestBody Course course) {
        return courseClientFeign.updateCourse(course);
    }

    @GetMapping("/{courseId}")
    public CourseDTO findCourseById(@PathVariable("courseId") Long courseId) {
        return courseClientFeign.findCourseById(courseId);
    }

    @GetMapping("/findCourseByName")
    public List<Course> findCourseByName(@RequestParam String courseName) {
        return courseClientFeign.findCourseByName(courseName);
    }

    @GetMapping("/all")
    public List<List<CourseDTO>> findAllCourse() {
        List<List<CourseDTO>> services = new LinkedList<>();
        services.add(courseClientFeign.findAllCourse());
        services.add(courseClientFeign.findCourseByStudentId(UserUtils.getCurrentUser().getUserId()));
        return services;
    }

    @GetMapping("/{courseId}/allStudent")
    public List<Student> findStudentByCourseId(@PathVariable("courseId") Long courseId) {
        return courseClientFeign.findStudentByCourseId(courseId);
    }

    @GetMapping("/{courseId}/allHomework")
    public List<HomeworkDTO> findHomeWorkByCourseId(@PathVariable("courseId") Long courseId) {
        return courseClientFeign.findHomeWorkByCourseId(courseId, UserUtils.getCurrentUser().getUserId());
    }

    @GetMapping("/getHomeworkByCourseId/{id}")
    public List<HomeWork> getHomeworkByCourseId(@PathVariable("id") Long courseId) {
        return courseClientFeign.getHomeworkByCourseId(courseId);
    }

    @PostMapping("/chooseCourse/{id}")
    public int chooseCourse(@PathVariable("id") Long courseId){
        Long userId = UserUtils.getCurrentUser().getUserId();
        return courseClientFeign.chooseCourse(userId, courseId);
    }

    @DeleteMapping("/withdrawCourse/{id}")
    public int withdrawCourse(@PathVariable("id") Long courseId) {
        Long userId = UserUtils.getCurrentUser().getUserId();
        return courseClientFeign.withdrawCourse(userId, courseId);
    }

    @GetMapping("/findCourseByStudentId/{studentId}")
    public List<CourseDTO> findCourseByStudentId(@PathVariable("studentId") Long id) {
        return courseClientFeign.findCourseByStudentId(id);
    }

    @GetMapping("/findCourseByStatus")
    public List<CourseDTO> findCourseByStatus(@RequestParam String status) {
        return courseClientFeign.findCourseByStatus(status);
    }

    @GetMapping("/findCurCourse/{teacherId}")
    public List<Course> findCurCourse(@PathVariable("teacherId") Long teacherId) {
        return courseClientFeign.findCurCourse(teacherId);
    }

    @GetMapping("/findPreCourse/{teacherId}")
    public List<Course> findPreCourse(@PathVariable("teacherId") Long teacherId) {
        return courseClientFeign.findPreCourse(teacherId);
    }
}
