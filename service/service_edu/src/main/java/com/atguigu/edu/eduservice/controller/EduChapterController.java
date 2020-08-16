package com.atguigu.edu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.eduservice.entity.EduChapter;
import com.atguigu.edu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-30
 */
@Api(description = "章节管理")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //  课程大纲，根据id进行查询
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId ){

        List<ChapterVo> chapterVideo=chapterService.getChapterVideoByCourseId(courseId);
        System.out.println(chapterVideo);
        return R.ok().data("chapterVideo",chapterVideo);
    }
    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter chapter){

        boolean save = chapterService.save(chapter);
        return R.ok();
    }
    //根据章节id进行查询
    @GetMapping("/getChapterInfo/{chapterid}")
    public R getChapterInfo(@PathVariable String chapterid){

        EduChapter eduChapter = chapterService.getById(chapterid);
        return R.ok().data("chapter",eduChapter);
    }
    //修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){

        boolean save = chapterService.updateById(chapter);
        return R.ok();
    }
    //删除章节
    @DeleteMapping("{chapterId}")
    public R deleteChapterByid(@PathVariable String chapterId){

        Boolean flag=chapterService.deleteChapterById(chapterId);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }

    }
}

