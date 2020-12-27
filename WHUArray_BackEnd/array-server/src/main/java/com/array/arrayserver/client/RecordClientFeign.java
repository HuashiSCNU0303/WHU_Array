package com.array.arrayserver.client;

import com.array.commonmodule.bean.Record;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yee
 */
@FeignClient(value = "COURSE-DATA-SERVICE")
@Service
public interface RecordClientFeign {
    @PostMapping("/Record/addRecord")
    public int addRecord(@RequestBody Record record);

    @GetMapping("/Record/findRecordByQuestionIdAndUserId")
    public List<Record> findRecordByQuestionIdAndUserId(@RequestParam Long questionId, @RequestParam Long userId);

    @GetMapping("/Record/findRecordByUserId")
    public List<Record> findRecordByUserId(@RequestParam Long userId);

    @GetMapping("/Record/findRecordByUserIdAndHomeworkId")
    public List<Record> findRecordByUserIdAndHomeworkId(@RequestParam Long userId, @RequestParam Long homeworkId);
}
