package com.atguigu.edu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.edu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-30
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("/addCouserInfo")
    public R addCouserInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后的课程id，为了后面添加大纲使用
        String id=courseService.save(courseInfoVo);
        return R.ok().data("courseid",id);
    }

    //根据id获取课程详情数据，作为回显
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getgetCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("/updateCouserInfo")
    public R updateCouserInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);
        return  R.ok();
    }

}

