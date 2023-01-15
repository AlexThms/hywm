package com.example.hywm.controller;

import com.example.hywm.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 21:41
 **/
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${hywm.path}")
    private String basePath;

    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
        long time = new Date().getTime();
        fileName = fileName + Long.toString(time);
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(fileName);
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse res){
        try {
            //输入流读取
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + fileName));
            //输出流写回浏览器
            ServletOutputStream outputStream = res.getOutputStream();
            res.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len=fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
