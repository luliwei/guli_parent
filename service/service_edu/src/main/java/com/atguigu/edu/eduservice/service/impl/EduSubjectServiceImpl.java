package com.atguigu.edu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.eduservice.entity.EduSubject;
import com.atguigu.edu.eduservice.entity.excel.SubjectData;
import com.atguigu.edu.eduservice.entity.subject.OneSubject;
import com.atguigu.edu.eduservice.entity.subject.TwoSubjeect;
import com.atguigu.edu.eduservice.listener.SubjectExcelListener;
import com.atguigu.edu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void ReadExcelSaveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in=file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getOneTwoSubject() {

        //1.获取所有的一级分类
        QueryWrapper<EduSubject> oneWrapper=new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(oneWrapper);

        //2.获取所有的二级分类
        QueryWrapper<EduSubject> twoWrapper=new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoWrapper);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList=new ArrayList<>();

        //3.封装一级分类
        for (int i=0;i<oneSubjectList.size();i++){
            EduSubject oneEduSubject=oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();

            //BeanUtils.copyProperties()方法，可以把eduSubject中的属性值，赋值给oneSubject中对应的属性
            BeanUtils.copyProperties(oneEduSubject,oneSubject);


            //在一级分类循环中遍历查询所有的二级分类
            //创建list集合，存放每一个一级分类中的二级分类
            List<TwoSubjeect> twoFinalSubjectList= new ArrayList<>();

            for (int j=0;j<twoSubjectList.size();j++){
                EduSubject twoEdusubject=twoSubjectList.get(j);
                TwoSubjeect twoSubjeect=new TwoSubjeect();
                if (twoEdusubject.getParentId().equals(oneSubject.getId())){
                    //封装二级分类
                    BeanUtils.copyProperties(twoEdusubject,twoSubjeect);

                    //将当前二级分类添加到集合中
                    twoFinalSubjectList.add(twoSubjeect);

                    //在二级分类集合中删除当前二级分类，减少遍历次数
                    twoSubjectList.remove(j);
                }
            }
            //将二级分类集合，赋给对应一级分类的children属性
            oneSubject.setChildren(twoFinalSubjectList);

            //多个oneSubject放入finalSubjectList
            finalSubjectList.add(oneSubject);
        }

        System.out.println(finalSubjectList);

        return finalSubjectList;
    }
}
