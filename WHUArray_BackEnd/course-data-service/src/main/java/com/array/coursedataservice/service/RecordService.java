package com.array.coursedataservice.service;

import com.array.commonmodule.bean.HomeWork;
import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.Record;
import com.array.commonmodule.bean.dto.QuestionDTO;
import com.array.coursedataservice.mapper.HomeworkMapper;
import com.array.coursedataservice.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 */
@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    public int addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    public List<Record> findRecordByQuestionIdAndUserId(Long questionId, Long userId) {
        return recordMapper.findRecordByQuestionIdAndUserId(questionId, userId);
    }

    public List<Record> findRecordByUserId(Long userId) {
        return recordMapper.findRecordByUserId(userId);
    }


    public List<Record> findRecordByUserIdAndHomeworkId(Long userId, Long homeworkId) {
        List<QuestionDTO> questions = homeworkMapper.findQuestionByHomeworkId(homeworkId);
        List<Long> ids = new LinkedList<>();
        for(long i = 0; i < questions.size(); i++) {
            ids.add(i);
        }
        return recordMapper.findRecordByUserIdAndHomeworkId(userId, ids);
    }
}
