package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {
    @Autowired
    CommonService commonService;
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file){
        String filePath = commonService.upload(file);
        return Result.success(filePath);
    }
}
