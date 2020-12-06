package com.whuarray.compiler.entity;

import java.util.Map;

public class DebugRequest {
    long problemID; // 题目ID
    String src; // 源代码
    String lang; // 所选语言(C++/Java/Python)
    String input; // 输入
    long debugTime;

    public long getProblemID() {
        return problemID;
    }

    public void setProblemID(long problemID) {
        this.problemID = problemID;
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public long getDebugTime() {
        return debugTime;
    }

    public void setDebugTime(long debugTime) {
        this.debugTime = debugTime;
    }
}
