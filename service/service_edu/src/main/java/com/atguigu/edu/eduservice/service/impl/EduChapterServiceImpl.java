package com.atguigu.edu.eduservice.service.impl;

import com.atguigu.edu.eduservice.entity.EduChapter;
import com.atguigu.edu.eduservice.entity.EduVideo;
import com.atguigu.edu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.edu.eduservice.entity.chapter.VideoVo;
import com.atguigu.edu.eduservice.handler.EduException;
import com.atguigu.edu.eduservice.mapper.EduChapterMapper;
import com.atguigu.edu.eduservice.service.EduChapterService;
import com.atguigu.edu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-30
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.查询当前课程下的所有章节
        QueryWrapper<EduChapter> chapterWrapper=new QueryWrapper<>();
        chapterWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterWrapper);

        //2.查询所有的小结
        QueryWrapper<EduVideo> videoWrapper=new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        List<EduVideo> videoList = videoService.list(videoWrapper);

        //创建list集合用于最终封装
        List<ChapterVo> finalList=new ArrayList<>();

        //3.遍历查询章节list集合，进行封装
        for (int i=0;i<chapterList.size();i++){
            EduChapter character=chapterList.get(i);
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(character,chapterVo);

            //获取当前章节id
            String cid=character.getId();
            //当前章节下的小节集合
            List<VideoVo> eduVideoList=new ArrayList<>();

            //4.遍历查询小节list集合，进行封装
            for (int j=0;j<videoList.size();j++){
                EduVideo eduVideo=videoList.get(j);
                //判断小节的所属章节id，是否等于当前章节id
                if (eduVideo.getChapterId().equals(cid)){
                    //是的话，将当前小节添加到当前章节下的小节集合
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    eduVideoList.add(videoVo);
                }
            }
            //eduVideoList并赋值给chapterVo的children属性
            chapterVo.setChildren(eduVideoList);
            //将封装好的章节chapterVo，添加到最终集合中
            finalList.add(chapterVo);
         }

        return finalList;
    }

    @Override
    public Boolean deleteChapterById(String chapterId) {
        //1.根据章节id，查询是否有小节
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("chapter_id",chapterId);
        int count = videoService.count(queryWrapper);
        //根据数据记录数，判断是否删除该章节
        if(count>0){//查询出小节，不能删除
            throw new EduException(20001,"不能删除");
        }else {//没有查询出数据，可以删除
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}
