package com.atguigu.test.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.internal.runners.statements.Fail;

import java.util.ArrayList;
import java.util.List;

public class testEasyExcel {

    public static void main(String[] args) {
        String fileName="C:\\Users\\路力玮\\Desktop\\尚硅谷谷粒学院项目视频教程/write.xlsx";

        List<DemoData> list=new ArrayList();
        for (int i=0;i<10;i++){
           DemoData data=new DemoData();
           data.setSno(i);
           data.setSname("王"+i);
           list.add(data);
        }

        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(list);
    }

}
