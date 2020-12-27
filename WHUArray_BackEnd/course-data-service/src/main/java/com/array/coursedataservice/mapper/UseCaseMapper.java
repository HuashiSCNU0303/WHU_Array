package com.array.coursedataservice.mapper;


import com.array.commonmodule.bean.UseCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yee
 */
@Mapper
public interface UseCaseMapper {
    UseCase getUseCaseById(Long id);
    int addUseCases(@Param("useCases") List<UseCase> useCases);
    List<UseCase> getUseCaseByQuestionId(Long id);
    int deleteUseCaseByQuestionId(Long id);
}
