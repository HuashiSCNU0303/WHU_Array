package com.array.commonmodule.bean.dto;

import java.util.List;

public class TestList {
    private List<TestCase> testCaseList;
    private Long problemId;

    public TestList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public TestList() {
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }
}
