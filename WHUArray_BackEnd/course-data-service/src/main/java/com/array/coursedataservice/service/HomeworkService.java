package com.array.coursedataservice.service;

import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.StudentHomeworkAssociation;
import com.array.commonmodule.bean.dto.HomeworkDTO;
import com.array.commonmodule.bean.dto.HomeworkTodo;
import com.array.commonmodule.bean.dto.QuestionDTO;
import com.array.coursedataservice.mapper.CourseMapper;
import com.array.coursedataservice.mapper.HomeworkMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 */
@Service
public class HomeworkService {
    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    CourseMapper courseMapper;

    public int addHomework(HomeWork homeWork) {
        return homeworkMapper.addHomework(homeWork);
        // sendMessage();
    }

    public int deleteHomework(Long homeworkId) {
        return homeworkMapper.deleteHomework(homeworkId);
    }

    public int updateHomework(HomeWork homeWork) {
        return homeworkMapper.updateHomework(homeWork);
    }

    public HomeWork findHomeworkById(Long homeworkId) {
        return homeworkMapper.findHomeworkById(homeworkId);
    }

    public List<HomeWork> findAllHomeWork() {
        return homeworkMapper.findAllHomeWork();
    }

    public List<StudentHomeworkAssociation> findStudentHomeworkAssociationByCourseIdAndUserId(Long userId, Long courseId) {
        List<HomeworkDTO> homeWorkList = courseMapper.findHomeWorkByCourseId(courseId, userId);
        List<Long> ids = new LinkedList<>();
        for(long i = 0; i < homeWorkList.size(); i++) {
            ids.add(i);
        }
        return homeworkMapper.findStudentHomeworkAssociationByCourseIdAndUserId(userId, ids);
    }

    public int submitHomework(StudentHomeworkAssociation studentHomeworkAssociation) {
        return homeworkMapper.submitHomework(studentHomeworkAssociation);
    }
    public List<QuestionDTO> findQuestionByHomeworkId(Long homeworkId) {
        return homeworkMapper.findQuestionByHomeworkId(homeworkId);
    }

    public List<HomeworkTodo> findHomeWorkTodoListByUserId(Long id) {
        return homeworkMapper.findHomeWorkTodoListByUserId(id);
    }

    public int addStudentToHomework(Long homeworkId, List<Long> sids) {
        return homeworkMapper.addStudentToHomework(homeworkId, sids);
    }
}
