package com.array.commonmodule.bean;

import java.util.List;

/**
 * @author yee
 */
public class Question {
    private Long questionId;
    private Long homeworkId;
    private Long examId;
    private String questionName;
    private String questionContent;
    /**
     * 两种取值：已发布（"published"）/未发布（"unpublished"
     */
    private String status;
    private String tag;
    private List<UseCase> useCases;

    public Question() {
    }

    public Question(Long questionId, Long homeworkId, Long examId, String questionName, String questionContent, String status, String tag, List<UseCase> useCases) {
        this.questionId = questionId;
        this.homeworkId = homeworkId;
        this.examId = examId;
        this.questionName = questionName;
        this.questionContent = questionContent;
        this.status = status;
        this.tag = tag;
        this.useCases = useCases;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", homeworkId=" + homeworkId +
                ", examId=" + examId +
                ", questionName='" + questionName + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                ", useCases=" + useCases +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<UseCase> getUseCases() {
        return useCases;
    }

    public void setUseCases(List<UseCase> useCases) {
        this.useCases = useCases;
    }
}
