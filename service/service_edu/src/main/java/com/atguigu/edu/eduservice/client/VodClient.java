package com.atguigu.edu.eduservice.client;


import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "service-oss",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @PostMapping("/upload")
    public R uploatOssFile(MultipartFile file);
}
