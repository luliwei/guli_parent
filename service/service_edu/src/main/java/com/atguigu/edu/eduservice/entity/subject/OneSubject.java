package com.atguigu.edu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//一级分类
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubjeect> children=new ArrayList<>();
}
