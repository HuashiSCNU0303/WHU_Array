package com.array.commonmodule.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yee
 */
public class HomeWork {
    private Long homeworkId;
    private Long courseId;
    private String homeworkContent;
    private String homeworkName;
    private String startTime;
    private String endTime;
    private String status;
    private int isExam;

    public HomeWork() {
    }

    public HomeWork(Long homeworkId, Long courseId, String homeworkContent, String homeworkName, String startTime, String endTime, String status, int isExam) {
        this.homeworkId = homeworkId;
        this.courseId = courseId;
        this.homeworkContent = homeworkContent;
        this.homeworkName = homeworkName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.isExam = isExam;
    }

    @Override
    public String toString() {
        return "HomeWork{" +
                "homeworkId=" + homeworkId +
                ", courseId=" + courseId +
                ", homeworkContent='" + homeworkContent + '\'' +
                ", homeworkName='" + homeworkName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", isExam=" + isExam +
                '}';
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
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
}
