package com.whuarray.compiler.controller;

import com.whuarray.compiler.entity.DebugRequest;
import com.whuarray.compiler.utility.CommonUtils;
import com.whuarray.compiler.utility.Constant;
import com.whuarray.compiler.utility.StatusHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/debug")
@Controller
public class DebugController {

    public void successHandler(File submissionPath, Map<String, String> results) throws Exception {
        FileReader fr = new FileReader(submissionPath + "/output_info.txt");
        BufferedReader bf = new BufferedReader(fr);
        String str;
        int runTime = -1;
        StringBuilder currentDebugOutput = new StringBuilder();
        while ((str = bf.readLine()) != null) {
            if (str.startsWith("[TIME]")) {
                // 获取运行时间
                runTime = CommonUtils.matchNumber(str) / 1000000;
            }
            else {
                // 输出结果连接到一行字符串里面
                currentDebugOutput.append(str).append(System.lineSeparator());
            }
        }
        bf.close();
        fr.close();
        results.put("status", String.valueOf(Constant.STATUS_AC));
        results.put("result", currentDebugOutput.toString());
        results.put("time", String.valueOf(runTime));
    }

    public void runtimeErrorHandler(File submissionPath, Map<String, String> results) throws Exception {
        FileReader fr = new FileReader(submissionPath + "/runtime_error_info.txt");
        BufferedReader bf = new BufferedReader(fr);
        String str;
        StringBuilder runtimeErrorInfo = new StringBuilder();
        while ((str = bf.readLine()) != null) {
            runtimeErrorInfo.append(str).append(System.lineSeparator());
        }
        bf.close();
        fr.close();
        results.put("status", String.valueOf(Constant.STATUS_RE));
        results.put("info", runtimeErrorInfo.toString());
    }

    public void runProgram(DebugRequest debugRequest, File submissionPath, Map<String, String> results) throws Exception {
        // 运行脚本
        String scriptName = "./" + debugRequest.getLang() + "_debug.sh";
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(Constant.BASE_PATH));
        builder.command(scriptName, String.valueOf(debugRequest.getProblemID()), String.valueOf(debugRequest.getDebugTime()));
        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode == Constant.EXITCODE_SU) {
            // 运行成功，读取output_info.txt文件获取信息
            successHandler(submissionPath, results);
        }
        else if (exitCode == Constant.EXITCODE_RE) {
            // 运行失败，读取runtime_error_info.txt文件获取信息
            runtimeErrorHandler(submissionPath, results);
        }
        else if (exitCode == Constant.EXITCODE_CE) {
            // 编译失败（仅C++），读取compile_info.txt文件获取信息
            StatusHandler.compileErrorHandler(submissionPath, results);
        }
    }

    @PostMapping
    @ResponseBody
    public Map<String, String> debug(@RequestBody DebugRequest debugRequest) throws Exception {
        debugRequest.setDebugTime(System.currentTimeMillis());
        String chosenLang = debugRequest.getLang();
        Map<String, String> results = new HashMap<>();
        String[] strings = {Constant.BASE_PATH + debugRequest.getProblemID() + "/debug/",  debugRequest.getDebugTime() + File.separator, "Main." + chosenLang, "input"};
        Path submissionPath = Paths.get(strings[0] + strings[1]);
        Path srcPath = Paths.get(strings[0] + strings[1] + strings[2]);
        Path inputPath = Paths.get(strings[0] + strings[1] + strings[3]);
        if (Files.notExists(submissionPath)) {
            Files.createDirectory(submissionPath);
        }
        CommonUtils.createFile(srcPath, debugRequest.getSrc());
        CommonUtils.createFile(inputPath, debugRequest.getInput());
        if ("java".equals(chosenLang)) {
            // 编译源文件
            if (CommonUtils.compileJavaProgram(srcPath, results)) {
                // 编译成功，运行脚本，执行程序
                runProgram(debugRequest, submissionPath.toFile(), results);
            }
        }
        else {
            runProgram(debugRequest, submissionPath.toFile(), results);
        }
        CommonUtils.deleteDir(submissionPath.toFile());
        return results;
    }
}
