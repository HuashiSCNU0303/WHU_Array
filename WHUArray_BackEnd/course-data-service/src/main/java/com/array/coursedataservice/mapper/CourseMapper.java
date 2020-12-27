package com.array.coursedataservice.mapper;

import com.array.commonmodule.bean.*;
import com.array.commonmodule.bean.dto.CourseDTO;
import com.array.commonmodule.bean.dto.HomeworkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yee
 */
@Mapper
public interface CourseMapper {
    int addCourse(Course course);

    int deleteCourse(Long courseId);

    int updateCourse(Course course);

    CourseDTO findCourseById(Long courseId);

    List<Course> findCourseByName(String courseName);

    List<CourseDTO> findAllCourse();

    List<Student> findStudentByCourseId(Long courseId);

    List<HomeWork> getHomeworkByCourseId(Long courseId);

    List<HomeworkDTO> findHomeWorkByCourseId(@Param("courseId") Long courseId, @Param("userId") Long userId);

    int chooseCourse(@Param("userId") Long userId, @Param("courseId")  Long courseId);

    int withdrawCourse(@Param("userId") Long userId, @Param("courseId") Long courseId);

    List<CourseDTO> findCourseByStudentId(Long id);

    List<CourseDTO> findCourseByStatus(String status);

    List<Course> findCurCourse(Long teacherId);

    List<Course> findPreCourse(Long teacherId);
}
