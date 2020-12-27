package com.array.coursedataservice.service;

import com.array.commonmodule.bean.Course;
import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Student;
import com.array.commonmodule.bean.User;
import com.array.commonmodule.bean.dto.CourseDTO;
import com.array.commonmodule.bean.dto.HomeworkDTO;
import com.array.coursedataservice.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yee
 */
@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    public int deleteCourse(Long courseId) {
        return courseMapper.deleteCourse(courseId);
    }

    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    public CourseDTO findCourseById(Long courseId) {
        return courseMapper.findCourseById(courseId);
    }

    public List<HomeWork> getHomeworkByCourseId(Long courseId) {
        return courseMapper.getHomeworkByCourseId(courseId);
    }

    public List<Course> findCourseByName(String courseName) {
        return courseMapper.findCourseByName(courseName);
    }

    public List<CourseDTO> findAllCourse() {
        return courseMapper.findAllCourse();
    }

    public List<Student> findStudentByCourseId(Long courseId) {
        return courseMapper.findStudentByCourseId(courseId);
    }

    public List<HomeworkDTO> findHomeWorkByCourseId(Long courseId, Long userId) {
        return courseMapper.findHomeWorkByCourseId(courseId, userId);
    }

    public int chooseCourse(Long userId, Long courseId){
        return courseMapper.chooseCourse(userId, courseId);
    }

    public List<CourseDTO> findCourseByStudentId(Long id) {
        return courseMapper.findCourseByStudentId(id);
    }

    public List<CourseDTO> findCourseByStatus(String status) {
        return courseMapper.findCourseByStatus(status);
    }

    public int withdrawCourse(Long userId, Long courseId) {
        return courseMapper.withdrawCourse(userId, courseId);
    }

    public List<Course> findCurCourse(Long teacherId) {
        return courseMapper.findCurCourse(teacherId);
    }

    public List<Course> findPreCourse(Long teacherId) {
        return courseMapper.findPreCourse(teacherId);
    }
}
