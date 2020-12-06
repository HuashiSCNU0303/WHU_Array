package com.whuarray.compiler.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.whuarray.compiler.entity.TestCaseRequest;
import com.whuarray.compiler.utility.CommonUtils;
import com.whuarray.compiler.utility.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/testcase")
@Controller
public class TestCaseController {

    @PostMapping("/add")
    @ResponseBody
    public Map<String, String> addTestCases(@RequestBody String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("testCaseList");
        List<TestCaseRequest> testCaseList = jsonArray.toJavaList(TestCaseRequest.class);
        Map<String, String> results = new HashMap<>();
        try {
            for (TestCaseRequest testCase : testCaseList) {
                int problemID = testCase.getProblemID();
                String problemPath_str = Constant.BASE_PATH + problemID + File.separator;
                String testCaseDirPath_str = Constant.BASE_PATH + problemID + File.separator + "TestCase" + File.separator;
                Path problemPath = Paths.get(problemPath_str), testCaseDirPath = Paths.get(testCaseDirPath_str);
                if (Files.notExists(problemPath)) {
                    Files.createDirectory(problemPath);
                    Files.createDirectory(Paths.get(problemPath_str + "debug/"));
                    Files.createDirectory(testCaseDirPath);
                }
                Path testCasePath = Paths.get(testCaseDirPath_str + testCase.getTestCaseID());
                // 不存在就创建，存在了就覆盖
                if (!Files.notExists(testCasePath)) {
                    testCasePath.toFile().delete();

                }
                CommonUtils.createFile(testCasePath, testCase.getInput());
            }
            results.put("status", String.valueOf(Constant.TESTCASE_OP_SU));
        }
        catch (Exception e) {
            results.put("status", String.valueOf(Constant.TESTCASE_OP_ER));
            results.put("info", e.getMessage());
        }
        return results;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, String> deleteTestCases(@RequestBody String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("testCaseList");
        List<TestCaseRequest> testCaseList = jsonArray.toJavaList(TestCaseRequest.class);
        Map<String, String> results = new HashMap<>();
        try {
            for (TestCaseRequest testCase : testCaseList) {
                int problemID = testCase.getProblemID();
                String problemPath_str = Constant.BASE_PATH + problemID + File.separator;
                String testCaseDirPath_str = Constant.BASE_PATH + problemID + File.separator + "TestCase" + File.separator;
                Path problemPath = Paths.get(problemPath_str), testCaseDirPath = Paths.get(testCaseDirPath_str);
                // 题目不存在，不管
                if (Files.notExists(problemPath)) {
                    continue;
                }
                Path testCasePath = Paths.get(testCaseDirPath_str + testCase.getTestCaseID());
                // 存在了就删掉，不存在就不管了
                if (!Files.notExists(testCasePath)) {
                    testCasePath.toFile().delete();
                }
            }
            results.put("status", String.valueOf(Constant.TESTCASE_OP_SU));
        }
        catch (Exception e) {
            results.put("status", String.valueOf(Constant.TESTCASE_OP_ER));
            results.put("info", e.getMessage());
        }
        return results;
    }
}
