package com.array.commonmodule.bean.dto;


/**
 * @author yee
 */
public class CourseDTO {
    Long id;
    String name;
    String time;
    String teacher;
    String description;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String time, String teacher, String description) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.teacher = teacher;
        this.description = description;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", teacher='" + teacher + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
