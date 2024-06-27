package com.sky.service.impl;

import com.sky.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {
    @Override
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        int index = originalFilename.lastIndexOf(".");
        String extendTime = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + extendTime;
        log.info("新增的菜品图片名称：{}",newFilename);

        try {
            file.transferTo(new File("E:\\Code\\JavaCode\\cq_Takeaway\\sky-take-out\\sky-server\\src\\main\\resources\\static\\img\\"+newFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "E:\\Code\\JavaCode\\cq_Takeaway\\sky-take-out\\sky-server\\src\\main\\resources\\static\\img\\"+newFilename;
    }
}
