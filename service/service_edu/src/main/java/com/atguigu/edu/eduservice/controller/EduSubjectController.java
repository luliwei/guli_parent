package com.atguigu.edu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.eduservice.entity.EduSubject;
import com.atguigu.edu.eduservice.entity.subject.OneSubject;
import com.atguigu.edu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("/addSubject")
    public R readeEduSubject(MultipartFile file){
        subjectService.ReadExcelSaveSubject(file,subjectService);

        return R.ok();
    }

    //课程分类列表(树形)
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        //list集合泛型是一级分类
        List<OneSubject> list=subjectService.getOneTwoSubject();
        return R.ok().data("list",list);
    }
}

