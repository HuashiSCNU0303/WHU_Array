package com.array.commonmodule.bean.dto;

public class HomeworkDTO {
    private Long homeworkId;
    private Long courseId;
    private String homeworkContent;
    private String homeworkName;
    private String startTime;
    private String endTime;
    private String status;
    private int isExam;
    private int grade;

    public HomeworkDTO() {
    }

    public HomeworkDTO(Long homeworkId, Long courseId, String homeworkContent, String homeworkName, String startTime, String endTime, String status, int isExam, int grade) {
        this.homeworkId = homeworkId;
        this.courseId = courseId;
        this.homeworkContent = homeworkContent;
        this.homeworkName = homeworkName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.isExam = isExam;
        this.grade = grade;
    }

    public HomeworkDTO(Long homeworkId, Long courseId, String homeworkContent, String homeworkName, String startTime, String endTime, String status, int isExam) {
        this.homeworkId = homeworkId;
        this.courseId = courseId;
        this.homeworkContent = homeworkContent;
        this.homeworkName = homeworkName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.isExam = isExam;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIsExam() {
        return isExam;
    }

    public void setIsExam(int isExam) {
        this.isExam = isExam;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
