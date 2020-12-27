package com.array.coursedataservice.controller;

import com.array.commonmodule.bean.UseCase;
import com.array.coursedataservice.service.UseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UseCase")
public class UseCaseController {

    @Autowired
    UseCaseService useCaseService;

    @GetMapping("/getUseCaseById/{id}")
    public UseCase getUseCaseById(@PathVariable("id") Long id) {
        return useCaseService.getUseCaseById(id);
    }

    @GetMapping("/{id}")
    public List<UseCase> getUseCaseByQuestionId(@PathVariable("id") Long id) {
        return useCaseService.getUseCaseByQuestionId(id);
    }
}
