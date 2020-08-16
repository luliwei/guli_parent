package com.atguigu.edu.eduservice.handler;

import com.atguigu.commonutils.R;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //aop思想，对前项目中所有出现的异常做处理
@Slf4j
public class GlobalExceptionHandler {

    //1.对所有的异常进行了相同的处理

    @ExceptionHandler(Exception.class)//指定对那些异常做处理
    @ResponseBody  //将异常信息相应到页面
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("出现了异常");
    }

    //2.对特定异常进行处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("0不能作为除数，出现了异常!");
    }

    //3.自定义异常
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e){
        log.error(e.getMessage());//把异常信息输出到文件中
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
