package com.array.commonmodule.bean;

/**
 * @author yee
 */
public class StudentHomeworkAssociation {
    private Long id;
    private Long userId;
    private Long homeworkId;
    private int grade;

    public StudentHomeworkAssociation() {
    }

    public StudentHomeworkAssociation(Long id, Long userId, Long homeworkId, int grade) {
        this.id = id;
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentHomeworkAssociation{" +
                "id=" + id +
                ", userId=" + userId +
                ", homeworkId=" + homeworkId +
                ", grade=" + grade +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
