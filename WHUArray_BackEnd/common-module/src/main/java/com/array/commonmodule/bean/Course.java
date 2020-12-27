package com.array.commonmodule.bean;

import java.util.List;

/**
 * @author yee
 */
public class Course {
    private Long courseId;
    private Long examId;
    private Long teacherId;
    private String courseName;
    private String courseTime;
    private String status;
    private String grade;
    private String description;


    public Course() {
    }

    public Course(Long courseId, Long examId, Long teacherId, String courseName, String courseTime, String status, String grade, String description) {
        this.courseId = courseId;
        this.examId = examId;
        this.teacherId = teacherId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.status = status;
        this.grade = grade;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", examId=" + examId +
                ", teacherId=" + teacherId +
                ", courseName='" + courseName + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", status='" + status + '\'' +
                ", grade='" + grade + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
