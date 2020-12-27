package com.array.coursedataservice.mapper;

import com.array.commonmodule.bean.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yee
 */
@Mapper
public interface RecordMapper {
    int addRecord(Record record);

    List<Record> findRecordByQuestionIdAndUserId(@Param("questionId") Long questionId, @Param("userId") Long userId);

    List<Record> findRecordByUserId(Long userId);

    List<Record> findRecordByUserIdAndHomeworkId(Long userId, List<Long> ids);
}
