package com.array.arrayserver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.array.arrayserver.client.RecordClientFeign;
import com.array.arrayserver.client.UseCaseClientFeign;
import com.array.commonmodule.bean.Record;
import com.array.commonmodule.bean.UseCase;
import com.array.commonmodule.bean.dto.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Service
public class CompileService {

    @Autowired
    RecordClientFeign recordClientFeign;

    @Autowired
    UseCaseClientFeign useCaseClientFeign;

    public String addTestCases(TestList testList) {
        Object obj = JSONObject.toJSON(testList);
        String json = obj.toString();
        System.out.println(json);
        String result = "";
        String url = "http://192.168.193.154:8080/testcase/set";
        HttpPost post = new HttpPost(url);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            post.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(json, "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);

            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                strber.append(line + '\n');
            }
            br.close();
            in.close();
            result = strber.toString();
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                result = "服务器异常";
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        } finally {
            post.abort();
        }
        return result;
    }

    public String deleteTestCases(List<TestCase> testCaseList) {
        Object obj = JSONArray.toJSON(testCaseList);
        String json = obj.toString();
        String result = "";
        String url = "http://192.168.193.154:8080/testcase/delete";
        HttpPost post = new HttpPost(url);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            post.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(json, "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);

            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                strber.append(line + '\n');
            }
            br.close();
            in.close();
            result = strber.toString();
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                result = "服务器异常";
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        } finally {
            post.abort();
        }
        return result;
    }

    public String judge(Record record) {
        JudgeDTO judgeDTO = new JudgeDTO();
        judgeDTO.setProblemID(record.getQuestionId().toString());
        judgeDTO.setLang(record.getLang());
        judgeDTO.setSrc(record.getRecordContent());
        judgeDTO.setSubmitID(String.valueOf(System.currentTimeMillis()));
        List<UseCase> useCases = useCaseClientFeign.getUseCaseByQuestionId(record.getQuestionId());
        List<TestCaseAssociation> testCaseAssociations = new LinkedList<>();
        for(int i = 0; i < useCases.size(); i++) {
            TestCaseAssociation testCaseAssociation = new TestCaseAssociation();
            testCaseAssociation.setTestCaseId(useCases.get(i).getUseCaseId().toString());
            testCaseAssociation.setResult(useCases.get(i).getOutput());
            testCaseAssociations.add(testCaseAssociation);
        }
        judgeDTO.setTestCases(testCaseAssociations);
        String judgeString = JSON.toJSONString(judgeDTO);
        System.out.println(judgeString);
        String result = "";
        String url = "http://192.168.193.154:8080/judge";
        HttpPost post = new HttpPost(url);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            post.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(judgeString, "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);

            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                strber.append(line + '\n');
            }
            br.close();
            in.close();
            result = strber.toString();
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                result = "服务器异常";
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        } finally {
            post.abort();
        }
        System.out.println(result);
        JSONObject jo = JSONObject.parseObject(result);
        long grade;
        if(jo.get("status").toString().equals("1")) {
            grade = 100;
        }
        else if(jo.get("status").toString().equals("0")) {
            grade = 0;
        }
        else {
            grade = Integer.parseInt(jo.get("currentRightAnswerCount").toString()) / useCases.size();
            UseCase useCase = useCaseClientFeign.getUseCaseById(jo.getLong("wrongTestCaseID"));
            jo.put("wrongTestCaseInput", useCase.getInput());
        }
        jo.put("score", grade);
        record.setRecordGrade(grade);
        recordClientFeign.addRecord(record);
        return jo.toJSONString();
    }

    public String debug(DebugDTO debugDTO) {
        String debugString = JSON.toJSONString(debugDTO);
        String result = "";
        String url = "http://192.168.193.154:8080/debug";
        System.out.println(debugString);
        HttpPost post = new HttpPost(url);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            post.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(debugString, "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);

            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                strber.append(line + '\n');
            }
            br.close();
            in.close();
            result = strber.toString();
            System.out.println(result);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                result = "服务器异常";
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        } finally {
            post.abort();
        }
        return result;
    }
}
