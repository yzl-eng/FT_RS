package com.project.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping("/videoImg")
    public String videoImgUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) { // 检查文件是否为空
            try {
                // 保存文件到服务器
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:\\Users\\18197\\Desktop\\project\\FT_RS\\FT_RS_VUE\\FT_RS\\src\\assets\\video_imgs/" + file.getOriginalFilename());
                Files.write(path, bytes);

                // 返回上传成功后的文件链接地址
                return "src/assets/video_imgs/" + file.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @PostMapping("/newsImg")
    public String newsImgUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) { // 检查文件是否为空
            try {
                // 保存文件到服务器
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:\\Users\\18197\\Desktop\\project\\FT_RS\\FT_RS_VUE\\FT_RS\\src\\assets\\news_imgs/" + file.getOriginalFilename());
                Files.write(path, bytes);

                // 返回上传成功后的文件链接地址
                return "src/assets/news_imgs/" + file.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    }

