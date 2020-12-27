package com.whuarray.compiler.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whuarray.compiler.entity.JudgeRequest;
import com.whuarray.compiler.utility.CommonUtils;
import com.whuarray.compiler.utility.Constant;
import com.whuarray.compiler.utility.StatusHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/judge")
@Controller
public class JudgeController {

    public JudgeRequest parseRequestObject(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JudgeRequest judgeRequest = new JudgeRequest();
        judgeRequest.setSrc(jsonObject.getString("src"));
        judgeRequest.setLang(jsonObject.getString("lang"));
        judgeRequest.setProblemID(jsonObject.getLong("problemID"));
        judgeRequest.setSubmitID(jsonObject.getLong("submitID"));

        Map<Integer, String> testCases = new HashMap<>();
        JSONArray jsonArray = jsonObject.getJSONArray("testCases");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            int testCaseID = object.getInteger("testCaseId");
            String result = object.getString("result");
            testCases.put(testCaseID, result);
        }

        judgeRequest.setTestCaseResults(testCases);
        return judgeRequest;
    }

    public void acceptedHandler(List<Integer> times, Map<String, String> results) {
        results.put("status", String.valueOf(Constant.STATUS_AC));
        int timeSum = 0;
        for (Integer time: times) {
            timeSum += time;
        }
        double timeSum_avg = (double)timeSum / times.size();
        results.put("time", String.valueOf(timeSum_avg));
    }

    public void wrongAnswerHandler(Map<String, String> results, Map<String, String> wrongAnswer, int currentRightAnswerCount) {
        results.put("status", String.valueOf(Constant.STATUS_WA));
        results.put("wrongTestCaseID", wrongAnswer.get("testCaseID"));
        results.put("wrongAnswer", wrongAnswer.get("wrongAnswer"));
        results.put("rightAnswer", wrongAnswer.get("rightAnswer"));
        results.put("currentRightAnswerCount", String.valueOf(currentRightAnswerCount));
    }

    public void runtimeErrorHandler(Map<String, String> results, int currentRightAnswerCount, int testCaseID, String info) {
        results.put("status", String.valueOf(Constant.STATUS_RE));
        results.put("wrongTestCaseID", String.valueOf(testCaseID));
        results.put("currentRightAnswerCount", String.valueOf(currentRightAnswerCount));
        results.put("info", info);
    }

    public void runProgram(String language, File submissionPath, JudgeRequest judgeRequest, Map<String, String> results) throws Exception {
        // 运行脚本
        String scriptName = "./" + language + "_judge.sh";
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(scriptName, String.valueOf(judgeRequest.getProblemID()), String.valueOf(judgeRequest.getSubmitID()));
        builder.directory(new File(Constant.BASE_PATH));
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode == Constant.EXITCODE_SU) {
            // 运行成功，读取output_info.txt文件比对结果
            FileReader fr = new FileReader(submissionPath + "/output_info.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            ArrayList<Integer> testCaseTimes = new ArrayList<>();
            int currentTestCaseID = -1, currentRightAnswerCount = 0;
            StringBuilder currentTestCaseResult = new StringBuilder();
            Map<String, String> wrongAnswer = new HashMap<>();
            boolean isWrongAnswer = false;
            while ((str = bf.readLine()) != null) {
                if (str.startsWith("[TESTCASE]")) {
                    // 比对上一个测试用例的结果
                    if (currentTestCaseID != -1) {
                        String currentAnswer = currentTestCaseResult.toString();
                        currentAnswer = currentAnswer.substring(0, currentAnswer.length() - 1);
                        String rightAnswer = judgeRequest.getTestCaseResults().get(currentTestCaseID);
                        if (!currentAnswer.equals(rightAnswer)) {
                            // 答案错误，转去相应处理
                            // wrongAnswerHandler(results, currentTestCaseID, currentAnswer, rightAnswer, currentRightAnswerCount);
                            if (!isWrongAnswer) {
                                wrongAnswer.put("testCaseID", String.valueOf(currentTestCaseID));
                                wrongAnswer.put("wrongAnswer", currentAnswer);
                                wrongAnswer.put("rightAnswer", rightAnswer);
                                isWrongAnswer = true;
                            }
                            // return;
                        }
                        else {
                            currentRightAnswerCount++;
                        }
                    }
                    // 获取当前测试用例号
                    currentTestCaseID = CommonUtils.matchNumber(str);
                    currentTestCaseResult = new StringBuilder();
                }
                else if (str.startsWith("[TIME]")) {
                    // 获取该测试用例的运行时间（以ms为单位）
                    int testCaseTime = CommonUtils.matchNumber(str);
                    testCaseTime = testCaseTime / 1000000;
                    testCaseTimes.add(testCaseTime);
                }
                else {
                    // 输出结果连接到一行字符串里面
                    currentTestCaseResult.append(str).append(System.lineSeparator());
                }
            }
            bf.close();
            fr.close();
            if (isWrongAnswer) {
                wrongAnswerHandler(results, wrongAnswer, currentRightAnswerCount);
            }
            else {
                acceptedHandler(testCaseTimes, results);
            }
        }
        else if (exitCode == Constant.EXITCODE_RE) {
            // 运行出错，读取runtime_error_info.txt文件获取信息
            FileReader fr = new FileReader(submissionPath + "/runtime_error_info.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            StringBuilder runtimeErrorInfo = new StringBuilder();
            int wrongTestCaseID = -1, currentRightAnswerCount = 0;
            while ((str = bf.readLine()) != null) {
                if (str.startsWith("[TESTCASE]")) {
                    wrongTestCaseID = CommonUtils.matchNumber(str);
                }
                else if (str.startsWith("[CUR_RIGHT_ANS_COUNT]")) {
                    currentRightAnswerCount = CommonUtils.matchNumber(str);
                }
                else {
                    runtimeErrorInfo.append(str).append(System.lineSeparator());
                }
            }
            bf.close();
            fr.close();
            runtimeErrorHandler(results, wrongTestCaseID, currentRightAnswerCount, runtimeErrorInfo.toString());
        }
        else if (exitCode == Constant.EXITCODE_CE) {
            // 编译失败（仅C++），读取compile_info.txt文件获取信息
            StatusHandler.compileErrorHandler(submissionPath, results);
        }
    }

    @PostMapping
    @ResponseBody
    public Map<String, String> judge(@RequestBody String jsonString) throws Exception {
        JudgeRequest judgeRequest = parseRequestObject(jsonString);
        String chosenLang = judgeRequest.getLang();
        Map<String, String> results = new HashMap<>();
        String[] strings = {Constant.BASE_PATH + judgeRequest.getProblemID() + File.separator,  judgeRequest.getSubmitID() + File.separator, "Main." + chosenLang};
        Path problemPath = Paths.get(strings[0]);
        if (Files.notExists(problemPath)) {
            Files.createDirectory(problemPath);
        }
        Path submissionPath = Paths.get(strings[0]+strings[1]);
        Path path = Paths.get(strings[0]+strings[1]+strings[2]);
        if (Files.notExists(submissionPath)) {
            Files.createDirectory(submissionPath);
        }
        CommonUtils.createFile(path, judgeRequest.getSrc());

        if ("java".equals(chosenLang)) {
            // 编译源文件
            if (CommonUtils.compileJavaProgram(path, results)) {
                // 编译成功，运行脚本，执行程序
                runProgram(chosenLang, submissionPath.toFile(), judgeRequest, results);
            }
        }
        else {
            runProgram(chosenLang, submissionPath.toFile(), judgeRequest, results);
        }
        // CommonUtils.deleteDir(submissionPath.toFile());
        return results;
    }
}
