package com.whuarray.compiler.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class StatusHandler {
    public static void compileErrorHandler(File submissionPath, Map<String, String> results) throws Exception {
        FileReader fr = new FileReader(submissionPath + "/compile_info.txt");
        BufferedReader bf = new BufferedReader(fr);
        String str;
        StringBuilder compileErrorInfo = new StringBuilder();
        while ((str = bf.readLine()) != null) {
            compileErrorInfo.append(str).append(System.lineSeparator());
        }
        bf.close();
        fr.close();
        compileErrorHandler(compileErrorInfo.toString(), results);
    }

    public static void compileErrorHandler(String compileErrorInfo, Map<String, String> results) {
        results.put("status", String.valueOf(Constant.STATUS_CE));
        results.put("info", compileErrorInfo.toString());
    }
}
