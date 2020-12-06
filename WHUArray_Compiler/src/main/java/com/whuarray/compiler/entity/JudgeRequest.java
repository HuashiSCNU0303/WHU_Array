package com.whuarray.compiler.entity;

import java.util.List;
import java.util.Map;

public class JudgeRequest {
    long submitID; // 提交ID，可以把整个submit都传过来
    long problemID; // 题目ID
    Map<Integer, String> testCaseResults; // 测试用例，键值对表示为<测试用例ID，对应结果字符串>
    String src; // 源代码
    String lang; // 所选语言(C++/Java/Python)

    public long getSubmitID() {
        return submitID;
    }

    public void setSubmitID(long submitID) {
        this.submitID = submitID;
    }

    public long getProblemID() {
        return problemID;
    }

    public void setProblemID(long problemID) {
        this.problemID = problemID;
    }

    public Map<Integer, String> getTestCaseResults() {
        return testCaseResults;
    }

    public void setTestCaseResults(Map<Integer, String> testCaseResults) {
        this.testCaseResults = testCaseResults;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
