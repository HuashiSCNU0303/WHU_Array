package com.array.arrayserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.array.arrayserver.client.RecordClientFeign;
import com.array.arrayserver.client.UseCaseClientFeign;
import com.array.arrayserver.service.CompileService;
import com.array.commonmodule.bean.Record;
import com.array.commonmodule.bean.UseCase;
import com.array.commonmodule.bean.dto.*;
import jdk.nashorn.internal.ir.IdentNode;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yee
 */
@RestController
@RequestMapping("/compiler")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class CompilerController {

    @Autowired
    CompileService compileService;

    @PostMapping("/addTestCases")
    public String addTestCases(@RequestBody TestList testList) {
        return compileService.addTestCases(testList);
    }

    @PostMapping("/deleteTestCases")
    public String deleteTestCases(@RequestBody List<TestCase> testCaseList) {
        return compileService.deleteTestCases(testCaseList);
    }

    @PostMapping("/judge")
    public String judge(@RequestBody Record record) {
        return compileService.judge(record);
    }

    @PostMapping("/debug")
    public String debug(@RequestBody DebugDTO debugDTO) {
        return compileService.debug(debugDTO);
    }



}
