package com.array.arrayserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/file") // 通过服务器URL/file http://……:8009/rest/
public class FileController {
    @Value("${constant.base-url}")
    private String url;

    @Value("${constant.pic-file-resource-path}")
    private String path;

    @CrossOrigin
    @PostMapping("api") // http://……:8009/file/api 发POST请求 参数名file
    public @ResponseBody
    String fileUpload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        File Folder = new File(path);
        File f = new File(Folder, UUID.randomUUID()
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));

        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String URL = url+ "/images/" + f.getName();
            return URL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
