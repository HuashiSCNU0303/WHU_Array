package com.array.arrayserver.client;


import com.array.commonmodule.bean.UseCase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author yee
 */
@FeignClient(value = "COURSE-DATA-SERVICE")
@Service
public interface UseCaseClientFeign {

    @GetMapping("/UseCase/getUseCaseById/{id}")
    public UseCase getUseCaseById(@PathVariable("id") Long id);

    @GetMapping("/UseCase/{id}")
    public List<UseCase> getUseCaseByQuestionId(@PathVariable("id") Long id);
}
