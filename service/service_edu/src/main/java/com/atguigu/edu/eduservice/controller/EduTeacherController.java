package com.atguigu.edu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.atguigu.edu.eduservice.entity.EduTeacher;
import com.atguigu.edu.eduservice.entity.query.QueryTeacher;
import com.atguigu.edu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-03
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询所有讲师
    @GetMapping("/allteacher")
    public R getAllTescherList(){

        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    //根据id进行逻辑删除
    @DeleteMapping("{id}")
    public R deleteTeacherByid(@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页查询讲师列表的方法
    @GetMapping("pageList/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page,@PathVariable Long limit){
        //创建pageTeacher对象，传递两个参数
        Page pageTeacher=new Page(page,limit);
        //调用分页查询
        eduTeacherService.page(pageTeacher,null);
        //从pageTeacher对象中获取分页数据
        long total = pageTeacher.getTotal();
        List records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("items",records);
    }

    //多条件组合，进行分页查询
    @PostMapping("/MoreCondtionPageList/{page}/{limit}")
    public R getMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                     @RequestBody(required = false) QueryTeacher queryTeacher){
        //@RequestBody将前台数据转为json格式    (required = false)：数据可以不写

//        try {
//            int i=10/0;
//        } catch (Exception e) {
//           throw new EduException(501,"自定义异常");
//        }

        Page<EduTeacher> pageTeacher=new Page<>(page,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");
        //调用service层的方法实现，条件查询加分页
        //eduTeacherService.pageListCondtion(pageTeacher,queryTeacher);
        eduTeacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("items",records);
    }
    //添加讲师的方法
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);

        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据Id查询讲师
    @GetMapping("/getTeacherInfo/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }

    //根据id修改的方法
    @PostMapping("/updateTercherInfo")
    public R updateTercherById(@RequestBody EduTeacher eduTeacher){

        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }

    }

}

