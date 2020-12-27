package com.array.arrayserver.controller;

import com.array.arrayserver.Utils.UserUtils;
import com.array.arrayserver.client.RecordClientFeign;
import com.array.commonmodule.bean.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yee
 */
@RestController
@RequestMapping("/record")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class RecordController {
    @Autowired
    RecordClientFeign recordClientFeign;

    @PostMapping("/addRecord")
    public int addRecord(@RequestBody Record record) {
        return recordClientFeign.addRecord(record);
    }

    @GetMapping("/findRecordByQuestionIdAndUserId")
    public List<Record> findRecordByQuestionIdAndUserId(@RequestParam Long questionId, @RequestParam Long userId) {
        return recordClientFeign.findRecordByQuestionIdAndUserId(questionId, userId);
    }

    @GetMapping("/findRecordByUserId")
    public List<Record> findRecordByUserId(@RequestParam Long userId) {
        return recordClientFeign.findRecordByUserId(userId);
    }

    @GetMapping("/findRecordByUserIdAndHomeworkId")
    public List<Record> findRecordByUserIdAndHomeworkId(@RequestParam Long userId, @RequestParam Long homeworkId) {
        return recordClientFeign.findRecordByUserIdAndHomeworkId(userId, homeworkId);
    }


}
