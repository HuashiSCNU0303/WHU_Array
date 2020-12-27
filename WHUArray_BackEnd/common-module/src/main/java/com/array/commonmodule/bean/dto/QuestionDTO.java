package com.array.commonmodule.bean.dto;

import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.Record;

import java.util.List;

public class QuestionDTO {
    Long id;
    private String courseName;
    private String homeworkName;
    Question question;
    List<Record> records;

    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String courseName, String homeworkName, Question question, List<Record> records) {
        this.id = id;
        this.courseName = courseName;
        this.homeworkName = homeworkName;
        this.question = question;
        this.records = records;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "question=" + question +
                ", records=" + records +
                '}';
    }
}
