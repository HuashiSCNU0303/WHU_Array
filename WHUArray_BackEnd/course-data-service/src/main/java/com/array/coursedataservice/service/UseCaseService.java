package com.array.coursedataservice.service;

import com.array.commonmodule.bean.UseCase;
import com.array.coursedataservice.mapper.UseCaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseService {
    @Autowired
    UseCaseMapper useCaseMapper;

    public UseCase getUseCaseById(Long id) {
        return useCaseMapper.getUseCaseById(id);
    }

    public List<UseCase> getUseCaseByQuestionId(Long id) {
        return useCaseMapper.getUseCaseByQuestionId(id);
    }

}
