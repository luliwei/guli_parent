package com.atguigu.edu.eduservice.service.impl;

import com.atguigu.edu.eduservice.entity.EduCourse;
import com.atguigu.edu.eduservice.entity.EduCourseDescription;
import com.atguigu.edu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.edu.eduservice.handler.EduException;
import com.atguigu.edu.eduservice.mapper.EduCourseMapper;
import com.atguigu.edu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-30
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public CourseInfoVo getgetCourseInfo(String courseId) {
        //获取课程表中的数据
        EduCourse eduCourse = baseMapper.selectById(courseId);
        //用于封装课程详情
        CourseInfoVo courseInfoVo=new CourseInfoVo();

        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //获取课程详情表中的数据
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表中的数据
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update==0){
            throw new EduException(5000,"修改课程信息失败");
        }
        //修改课程描述表中的数据
        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public String save(CourseInfoVo courseInfoVo) {
        //1.向课程表中添加课程基本信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw new EduException(5000,"课程信息添加失败");
        }

        //获取添加之后的课程id
        String cid = eduCourse.getId();

        //2.向课程介绍表中添加数据
        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        boolean save = courseDescriptionService.save(courseDescription);

        return cid;
    }
}
