package com.atguigu.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atguigu.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.edu.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-03
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //条件查询加分页
    void pageListCondtion(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher);
}
