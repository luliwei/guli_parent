package com.atguigu.edu.eduservice.service;

import com.atguigu.edu.eduservice.entity.EduChapter;
import com.atguigu.edu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-30
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapterById(String chapterId);
}
