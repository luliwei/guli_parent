package com.atguigu.edu.eduservice.service;

import com.atguigu.edu.eduservice.entity.EduCourse;
import com.atguigu.edu.eduservice.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-30
 */
public interface EduCourseService extends IService<EduCourse> {

    String save(CourseInfoVo courseInfoVo);

    CourseInfoVo getgetCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
