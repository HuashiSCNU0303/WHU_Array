package com.array.coursedataservice.controller;

import com.array.commonmodule.bean.Question;
import com.array.commonmodule.bean.Record;
import com.array.coursedataservice.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/Record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @PostMapping("/addRecord")
    public int addRecord(@RequestBody Record record) {
        return recordService.addRecord(record);
    }

    @GetMapping("/findRecordByQuestionIdAndUserId")
    public List<Record> findRecordByQuestionIdAndUserId(@RequestParam Long questionId, @RequestParam Long userId) {
        return recordService.findRecordByQuestionIdAndUserId(questionId, userId);
    }

    @GetMapping("/findRecordByUserId")
    public List<Record> findRecordByUserId(@RequestParam Long userId) {
        return recordService.findRecordByUserId(userId);
    }

    @GetMapping("/findRecordByUserIdAndHomeworkId")
    public List<Record> findRecordByUserIdAndHomeworkId(@RequestParam Long userId, @RequestParam Long homeworkId) {
        return recordService.findRecordByUserIdAndHomeworkId(userId, homeworkId);
    }

}
