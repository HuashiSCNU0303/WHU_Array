package com.array.coursedataservice.controller;

import com.array.commonmodule.bean.Course;
import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Student;
import com.array.commonmodule.bean.User;
import com.array.commonmodule.bean.dto.CourseDTO;
import com.array.commonmodule.bean.dto.HomeworkDTO;
import com.array.commonmodule.bean.vo.CourseVO;
import com.array.coursedataservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/Course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/addCourse")
    public Long addCourse(@RequestBody Course course) {
//        Course course = new Course();
//        course.setCourseId(courseVO.getCourseId());
//        course.setCourseName(courseVO.getCourseName());
//        course.setTeacherId(courseVO.getTeacherId());
        courseService.addCourse(course);
        return course.getCourseId();
    }

    @DeleteMapping("/{courseId}")
    public int deleteCourse(@PathVariable("courseId") Long courseId) {
        return courseService.deleteCourse(courseId);
    }

    @PutMapping("/updateCourse")
    public int updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    @GetMapping("/{courseId}")
    public CourseDTO findCourseById(@PathVariable("courseId") Long courseId) {
        return courseService.findCourseById(courseId);
    }

    @GetMapping("/findCourseByName")
    public List<Course> findCourseByName(@RequestParam String courseName) {
        return courseService.findCourseByName(courseName);
    }

    @GetMapping("/getHomeworkByCourseId/{id}")
    public List<HomeWork> getHomeworkByCourseId(@PathVariable("id") Long courseId) {
        return courseService.getHomeworkByCourseId(courseId);
    }

    @GetMapping("/all")
    public List<CourseDTO> findAllCourse() {
        return courseService.findAllCourse();
    }

    @GetMapping("/{courseId}/allStudent")
    public List<Student> findStudentByCourseId(@PathVariable("courseId") Long courseId) {
        return courseService.findStudentByCourseId(courseId);
    }

    @GetMapping("/{courseId}/allHomework")
    public List<HomeworkDTO> findHomeWorkByCourseId(@PathVariable("courseId") Long courseId, @RequestParam Long userId) {
        return courseService.findHomeWorkByCourseId(courseId, userId);
    }

    @PostMapping("/chooseCourse")
    public int chooseCourse(@RequestParam Long userId, @RequestParam Long courseId){
        return courseService.chooseCourse(userId, courseId);
    }

    @DeleteMapping("/withdrawCourse")
    public int withdrawCourse(@RequestParam Long userId, @RequestParam Long courseId) {
        return courseService.withdrawCourse(userId, courseId);
    }

    @GetMapping("/findCourseByStudentId/{studentId}")
    public List<CourseDTO> findCourseByStudentId(@PathVariable("studentId") Long id) {
        return courseService.findCourseByStudentId(id);
    }

    @GetMapping("/findCourseByStatus")
    public List<CourseDTO> findCourseByStatus(@RequestParam String status) {
        return courseService.findCourseByStatus(status);
    }

    @GetMapping("/findCurCourse/{teacherId}")
    public List<Course> findCurCourse(@PathVariable("teacherId") Long teacherId) {
        return courseService.findCurCourse(teacherId);
    }

    @GetMapping("/findPreCourse/{teacherId}")
    public List<Course> findPreCourse(@PathVariable("teacherId") Long teacherId) {
        return courseService.findPreCourse(teacherId);
    }
}
