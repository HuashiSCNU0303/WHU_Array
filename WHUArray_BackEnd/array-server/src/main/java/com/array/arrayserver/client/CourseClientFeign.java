package com.array.arrayserver.client;

import com.array.commonmodule.bean.Course;
import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Student;
import com.array.commonmodule.bean.User;
import com.array.commonmodule.bean.dto.CourseDTO;
import com.array.commonmodule.bean.dto.HomeworkDTO;
import com.array.commonmodule.bean.vo.CourseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yee
 */
@FeignClient(value = "COURSE-DATA-SERVICE")
@Service
public interface CourseClientFeign {
    @PostMapping("/Course/addCourse")
    public Long addCourse(@RequestBody Course course);

    @DeleteMapping("/Course/{courseId}")
    public int deleteCourse(@PathVariable("courseId") Long courseId);

    @PutMapping("/Course/updateCourse")
    public int updateCourse(@RequestBody Course course);

    @GetMapping("/Course/{courseId}")
    public CourseDTO findCourseById(@PathVariable("courseId") Long courseId);

    @GetMapping("/Course/findCourseByName")
    public List<Course> findCourseByName(@RequestParam String courseName);

    @GetMapping("/Course/all")
    public List<CourseDTO> findAllCourse();

    @GetMapping("/Course/{courseId}/allStudent")
    public List<Student> findStudentByCourseId(@PathVariable("courseId") Long courseId);

    @GetMapping("/Course/{courseId}/allHomework")
    public List<HomeworkDTO> findHomeWorkByCourseId(@PathVariable("courseId") Long courseId, @RequestParam Long userId);

    @PostMapping("/Course/chooseCourse")
    int chooseCourse(@RequestParam Long userId, @RequestParam Long courseId);

    @GetMapping("/Course/getHomeworkByCourseId/{id}")
    public List<HomeWork> getHomeworkByCourseId(@PathVariable("id") Long courseId);

    @DeleteMapping("/Course/withdrawCourse")
    public int withdrawCourse(@RequestParam Long userId, @RequestParam Long courseId);

    @GetMapping("/Course/findCourseByStudentId/{studentId}")
    List<CourseDTO> findCourseByStudentId(@PathVariable("studentId") Long id);

    @GetMapping("/Course/findCourseByStatus")
    List<CourseDTO> findCourseByStatus(@RequestParam String status);

    @GetMapping("/Course/findCurCourse/{teacherId}")
    public List<Course> findCurCourse(@PathVariable("teacherId") Long teacherId);

    @GetMapping("/Course/findPreCourse/{teacherId}")
    public List<Course> findPreCourse(@PathVariable("teacherId") Long teacherId);
}
