package com.atguigu.edu.eduservice.service;

import com.atguigu.edu.eduservice.entity.EduSubject;
import com.atguigu.edu.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
public interface EduSubjectService extends IService<EduSubject> {

    void ReadExcelSaveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getOneTwoSubject();
}
