package com.atguigu.edu.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atguigu.edu.eduservice.entity.EduTeacher;
import com.atguigu.edu.eduservice.entity.query.QueryTeacher;
import com.atguigu.edu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-03
 */
@Service  //该类父类中已注入basemapper，可直接使用
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageListCondtion(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher) {
        //根据queryTeacher传过来的值，判断，有条件值，拼接条件
        if(queryTeacher==null){
            //直接进行分页查询
            IPage<EduTeacher> eduTeacherIPage = baseMapper.selectPage(pageTeacher, null);
            return ;
        }
        String name=queryTeacher.getName();
        String level=queryTeacher.getLevel();
        String begin=queryTeacher.getBegin();
        String end=queryTeacher.getBegin();
        //StringUtils.isEmpty(name)判断字符串是否为null或者 " ";
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(pageTeacher, queryWrapper);
    }
}
