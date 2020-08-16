package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

@Service
public class OssserviceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4GJWpNDbQ6oEWwe5KyhK";
        String accessKeySecret ="wgy3uWj7FLGd1FmGisbahsg46PGS27";
        String bucketName = "guliedu-project";

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();
            //在文件名中添加唯一的uuid
            String uuid= UUID.randomUUID().toString().replace("-","");
            fileName=uuid+fileName;

            //把文件按照日期进行分类
            String datePath=new DateTime().toString("yyyy/MM/dd");

            //拼接  2020/4/1/文件名称
            fileName=datePath+"/"+fileName;

            //调用oss方法实现文件上传
            //第一个参数 bucketName
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
