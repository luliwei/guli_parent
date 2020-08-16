package com.atguigu.oss.controller;


import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService service;

    @PostMapping ("/upload")
    public R uploatOssFile(MultipartFile file){
        //获取上传文件 FileAvatar
        //返回上传到oss路径
        String url=service.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
    @GetMapping
    public R getData(){
        return R.ok();
    }
}
